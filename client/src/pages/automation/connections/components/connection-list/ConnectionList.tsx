import {Connection, Tag} from '@/shared/middleware/automation/configuration';
import {ComponentDefinitionBasic} from '@/shared/middleware/platform/configuration';

import ConnectionListItem from './ConnectionListItem';

const ConnectionList = ({
    componentDefinitions,
    connections,
    tags,
}: {
    componentDefinitions: ComponentDefinitionBasic[];
    connections: Connection[];
    tags: Tag[];
}) => {
    return (
        <ul className="w-full divide-y divide-border/50 px-4 2xl:mx-auto 2xl:w-4/5" role="list">
            {connections.map((connection) => {
                const connectionTagIds = connection.tags?.map((tag) => tag.id);

                return (
                    <ConnectionListItem
                        componentDefinitions={componentDefinitions}
                        connection={connection}
                        key={connection.id}
                        remainingTags={tags?.filter((tag) => !connectionTagIds?.includes(tag.id))}
                    />
                );
            })}
        </ul>
    );
};

export default ConnectionList;
