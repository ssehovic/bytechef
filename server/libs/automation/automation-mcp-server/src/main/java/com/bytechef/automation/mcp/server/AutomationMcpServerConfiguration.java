/*
 * Copyright 2025 ByteChef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytechef.automation.mcp.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.WebMvcSseServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpServerTransportProvider;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * @author Ivica Cardic
 */
@Configuration
public class AutomationMcpServerConfiguration {

//    private final ToolFacade toolFacade;

//    @SuppressFBWarnings("EI")
//    public McpServerConfiguration(ToolFacade toolFacade) {
//        this.toolFacade = toolFacade;
//    }

    @Bean
    WebMvcSseServerTransportProvider webMvcSseServerTransportProvider(ObjectMapper objectMapper) {
        return new WebMvcSseServerTransportProvider(objectMapper, "/api/automation/v1/mcp/message", "/automation/sse");
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction(WebMvcSseServerTransportProvider transportProvider) {
        return transportProvider.getRouterFunction();
    }

    @Bean
    McpSyncServer mcpServer(McpServerTransportProvider transportProvider) {
        var capabilities = McpSchema.ServerCapabilities.builder()
            .resources(false, true)
            .tools(true)
            .prompts(true)
            .logging()
            .build();

        return McpServer.sync(transportProvider)
            .serverInfo("bytechef-automation-mcp-server", "1.0.0")
            .capabilities(capabilities)
            .tools(McpToolUtils.toSyncToolSpecification(getToolCallbacks()))
            .build();
    }

    public List<ToolCallback> getToolCallbacks() {
        List<ToolCallback> toolCallbacks = new ArrayList<>();

//        List<ToolDTO> toolDTOs = toolFacade.getTools();
//
//        for (ToolDTO toolDTO : toolDTOs) {
//            FunctionToolCallback.Builder<Map<String, Object>, Object> builder = FunctionToolCallback
//                .builder(toolDTO.name(), getToolCallbackFunction(toolDTO.name()))
//                .inputType(Map.class)
//                .inputSchema(toolDTO.parameters());
//
//            if (toolDTO.description() != null) {
//                builder.description(toolDTO.description());
//            }
//
//            toolCallbacks.add(builder.build());
//        }

        return toolCallbacks;
    }

//    private Function<Map<String, Object>, Object> getToolCallbackFunction(String toolName) {
//        return null;
//        return request -> toolFacade.executeTool(toolName, request, Environment.PRODUCTION, null);
//    }
}
