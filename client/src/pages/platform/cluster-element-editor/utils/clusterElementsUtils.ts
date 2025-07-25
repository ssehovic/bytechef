import {ROOT_CLUSTER_HANDLE_STEP, ROOT_CLUSTER_WIDTH} from '@/shared/constants';
import {ComponentDefinition} from '@/shared/middleware/platform/configuration';
import {ClusterElementItemType, ClusterElementsType} from '@/shared/types';

export function initializeClusterElementsObject(
    clusterElementsData: ClusterElementsType,
    rootClusterElementDefinition: ComponentDefinition
) {
    const clusterElements: ClusterElementsType = {};

    if (!rootClusterElementDefinition.clusterElementTypes) {
        return clusterElements;
    }

    rootClusterElementDefinition.clusterElementTypes.forEach((elementType) => {
        const clusterElementType = convertNameToCamelCase(elementType.name || '');
        const elementData = clusterElementsData?.[clusterElementType];

        if (elementType.multipleElements) {
            if (Array.isArray(elementData) && elementData.length > 0) {
                clusterElements[clusterElementType] = elementData.map((element) => ({
                    clusterElements: element.clusterElements,
                    label: element.label,
                    metadata: element.metadata,
                    name: element.name,
                    parameters: element.parameters || {},
                    type: element.type,
                }));
            } else {
                clusterElements[clusterElementType] = [];
            }
        } else {
            if (elementData && isPlainObject(elementData)) {
                clusterElements[clusterElementType] = {
                    clusterElements: elementData.clusterElements,
                    label: elementData.label,
                    metadata: elementData.metadata,
                    name: elementData.name,
                    parameters: elementData.parameters || {},
                    type: elementData.type,
                };
            } else {
                clusterElements[clusterElementType] = null;
            }
        }
    });

    return clusterElements;
}

interface AddElementToClusterRootProps {
    clusterElementTypeLabel: string;
    clusterElementValue: ClusterElementItemType;
    clusterElements: ClusterElementsType;
    isMultipleElements: boolean;
}

export function addElementToClusterRoot({
    clusterElementTypeLabel,
    clusterElementValue,
    clusterElements,
    isMultipleElements,
}: AddElementToClusterRootProps) {
    if (isMultipleElements) {
        return {
            ...clusterElements,
            [clusterElementTypeLabel]: [
                ...(Array.isArray(clusterElements[clusterElementTypeLabel])
                    ? clusterElements[clusterElementTypeLabel]
                    : []),
                clusterElementValue,
            ],
        };
    } else {
        return {
            ...clusterElements,
            [clusterElementTypeLabel]: clusterElementValue,
        };
    }
}

export function calculateNodeWidth(handleCount: number): number {
    const baseWidth = ROOT_CLUSTER_WIDTH;
    const handleStep = ROOT_CLUSTER_HANDLE_STEP;

    if (!handleCount || handleCount <= 4) {
        return baseWidth;
    }

    return baseWidth + (handleCount - 4) * handleStep;
}

interface GetHandlePositionProps {
    index: number;
    handlesCount: number;
    nodeWidth: number;
}

export function getHandlePosition({handlesCount, index, nodeWidth}: GetHandlePositionProps): number {
    const nodeEdgeBuffer = nodeWidth * 0.1;

    const usableNodeWidth = nodeWidth - nodeEdgeBuffer * 2;

    if (handlesCount === 1) {
        return nodeWidth / 2;
    }

    const stepWidth = usableNodeWidth / (handlesCount - 1);

    const handlePosition = nodeEdgeBuffer + stepWidth * index;

    return handlePosition;
}

export function convertNameToCamelCase(typeName: string): string {
    let convertedTypeName = typeName.toLowerCase();

    if (typeName.includes('_')) {
        const [firstWord, ...remainingWords] = typeName.toLowerCase().split('_');

        const capitalizedWords = remainingWords.map((word) => word.charAt(0).toUpperCase() + word.slice(1));

        convertedTypeName = `${firstWord}${capitalizedWords.join('')}`;
    }

    return convertedTypeName;
}

export function convertNameToSnakeCase(type: string): string {
    return type.replace(/[A-Z]/g, (letter) => `_${letter.toLowerCase()}`).toUpperCase();
}

export function getClusterElementsLabel(clusterElementType: string) {
    clusterElementType = clusterElementType.replace(/([a-z])([A-Z])/g, '$1 $2');

    return clusterElementType.charAt(0).toUpperCase() + clusterElementType.slice(1);
}

export function isPlainObject(value: unknown): value is Record<string, unknown> {
    return typeof value === 'object' && value !== null && !Array.isArray(value);
}
