import {DEFAULT_NODE_POSITION} from '@/shared/constants';
import {ComponentDefinition, ComponentDefinitionApi} from '@/shared/middleware/platform/configuration';
import {
    ComponentDefinitionKeys,
    useGetComponentDefinitionQuery,
} from '@/shared/queries/platform/componentDefinitions.queries';
import {ClusterElementItemType, ClusterElementsType} from '@/shared/types';
import {useQueryClient} from '@tanstack/react-query';
import {Edge, Node} from '@xyflow/react';
import {useCallback, useEffect, useMemo, useState} from 'react';
import {useShallow} from 'zustand/react/shallow';

import useWorkflowDataStore from '../../workflow-editor/stores/useWorkflowDataStore';
import useWorkflowEditorStore from '../../workflow-editor/stores/useWorkflowEditorStore';
import {getLayoutedElements} from '../../workflow-editor/utils/layoutUtils';
import useClusterElementsDataStore from '../stores/useClusterElementsDataStore';
import {isPlainObject} from '../utils/clusterElementsUtils';
import createClusterElementsEdges from '../utils/createClusterElementsEdges';
import createClusterElementsNodes from '../utils/createClusterElementsNodes';

const useClusterElementsLayout = () => {
    const [nestedClusterRootsDefinitions, setNestedClusterRootsDefinitions] = useState<
        Record<string, ComponentDefinition>
    >({});

    const {rootClusterElementNodeData} = useWorkflowEditorStore();
    const {workflow} = useWorkflowDataStore.getState();

    const queryClient = useQueryClient();

    const mainClusterRootQueryParameters = useMemo(() => {
        if (!rootClusterElementNodeData?.type || !rootClusterElementNodeData?.componentName) {
            return {
                componentName: '',
                componentVersion: 1,
            };
        }

        return {
            componentName: rootClusterElementNodeData?.componentName,
            componentVersion: Number(rootClusterElementNodeData?.type?.split('/')[1]?.replace(/^v/, '')) || 1,
        };
    }, [rootClusterElementNodeData]);

    const {data: mainRootClusterElementDefinition} = useGetComponentDefinitionQuery(
        mainClusterRootQueryParameters,
        !!rootClusterElementNodeData?.workflowNodeName
    );

    const {nodes, setEdges, setNodes} = useClusterElementsDataStore(
        useShallow((state) => ({
            nodes: state.nodes,
            setEdges: state.setEdges,
            setNodes: state.setNodes,
        }))
    );

    const nodePositions = useMemo(
        () =>
            nodes.reduce<Record<string, {x: number; y: number}>>((accumulator, node) => {
                accumulator[node.id] = {
                    x: node.position.x,
                    y: node.position.y,
                };
                return accumulator;
            }, {}),
        [nodes]
    );

    const canvasWidth = window.innerWidth - 80;

    const workflowDefinitionTasks = useMemo(() => {
        if (!workflow.definition) {
            return [];
        }

        return JSON.parse(workflow.definition).tasks;
    }, [workflow.definition]);

    const mainRootClusterElementTask = workflowDefinitionTasks.find(
        (task: {name: string}) => task.name === rootClusterElementNodeData?.workflowNodeName
    );

    const {allNodes, taskEdges} = useMemo(() => {
        const nodes: Array<Node> = [];
        const edges: Array<Edge> = [];

        if (!rootClusterElementNodeData || !mainRootClusterElementDefinition || !workflow.definition) {
            return {allNodes: nodes, taskEdges: edges};
        }

        const mainRootClusterElementNode = {
            data: rootClusterElementNodeData,
            id: rootClusterElementNodeData.workflowNodeName,
            position:
                rootClusterElementNodeData.metadata?.ui?.nodePosition ||
                nodePositions[rootClusterElementNodeData.workflowNodeName] ||
                DEFAULT_NODE_POSITION,
            type: 'workflow',
        };

        nodes.push(mainRootClusterElementNode);

        const clusterElements = mainRootClusterElementTask.clusterElements || {};

        const clusterElementNodes = createClusterElementsNodes({
            clusterElements,
            clusterRootComponentDefinition: mainRootClusterElementDefinition,
            clusterRootId: rootClusterElementNodeData.workflowNodeName,
            currentNodePositions: nodePositions,
            nestedClusterRootsDefinitions: nestedClusterRootsDefinitions || {},
        });

        nodes.push(...clusterElementNodes);

        const clusterElementEdges = createClusterElementsEdges({
            clusterRootComponentDefinition: mainRootClusterElementDefinition,
            clusterRootId: rootClusterElementNodeData.workflowNodeName,
            nestedClusterRootsDefinitions: nestedClusterRootsDefinitions || {},
            nodes,
        });

        edges.push(...clusterElementEdges);

        return {allNodes: nodes, taskEdges: edges};

        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [nestedClusterRootsDefinitions, rootClusterElementNodeData, mainRootClusterElementDefinition, workflow]);

    const getClusterRootQueryParameters = useCallback(
        (elements: ClusterElementsType): Array<{componentName: string; componentVersion: number}> =>
            Object.values(elements).flatMap((value) => {
                if (Array.isArray(value)) {
                    return value.flatMap((item: ClusterElementItemType) => {
                        if (item.clusterElements) {
                            return [
                                {
                                    componentName: item.type.split('/')[0],
                                    componentVersion: Number(item.type?.split('/')[1]?.replace(/^v/, '')) || 1,
                                },
                                ...getClusterRootQueryParameters(item.clusterElements),
                            ];
                        }

                        return [];
                    });
                } else if (isPlainObject(value)) {
                    if (value.clusterElements) {
                        return [
                            {
                                componentName: value.type.split('/')[0],
                                componentVersion: Number(value.type?.split('/')[1]?.replace(/^v/, '')) || 1,
                            },
                            ...getClusterRootQueryParameters(value.clusterElements),
                        ];
                    }
                }

                return [];
            }),
        []
    );

    const getClusterRootDefinitionQuery = useCallback(
        (roots: Array<{componentName: string; componentVersion: number}>) =>
            roots.map((root) => ({
                componentName: root.componentName,
                componentVersion: root.componentVersion,
                queryFn: () =>
                    new ComponentDefinitionApi().getComponentDefinition({
                        componentName: root.componentName,
                        componentVersion: root.componentVersion,
                    }),
                queryKey: ComponentDefinitionKeys.componentDefinition({
                    componentName: root.componentName,
                    componentVersion: root.componentVersion,
                }),
            })),
        []
    );

    useEffect(() => {
        if (!rootClusterElementNodeData || !mainRootClusterElementDefinition || !workflow.definition) {
            return;
        }

        const clusterElements = mainRootClusterElementTask.clusterElements || {};
        const clusterRoots = getClusterRootQueryParameters(clusterElements);

        const fetchAndUpdateDefinitions = async () => {
            const clusterRootDefinitionQuery = getClusterRootDefinitionQuery(clusterRoots);
            const definitions: Record<string, ComponentDefinition> = {...nestedClusterRootsDefinitions};

            for (const query of clusterRootDefinitionQuery) {
                if (!definitions[query.componentName]) {
                    const definition = await queryClient.fetchQuery({
                        queryFn: query.queryFn,
                        queryKey: query.queryKey,
                    });

                    definitions[query.componentName] = definition;
                }
            }

            setNestedClusterRootsDefinitions(definitions);
        };

        fetchAndUpdateDefinitions();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [
        rootClusterElementNodeData,
        mainRootClusterElementDefinition,
        workflow.definition,
        queryClient,
        getClusterRootQueryParameters,
        getClusterRootDefinitionQuery,
        workflowDefinitionTasks,
    ]);

    useEffect(() => {
        const layoutNodes = allNodes;
        const edges: Edge[] = taskEdges;

        const elements = getLayoutedElements({canvasWidth, edges, isClusterElementsCanvas: true, nodes: layoutNodes});

        setNodes(elements.nodes);
        setEdges(elements.edges);

        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [canvasWidth, rootClusterElementNodeData, allNodes]);
};

export default useClusterElementsLayout;
