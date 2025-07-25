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

package com.bytechef.platform.component.service;

import com.bytechef.component.definition.Authorization.ApplyResponse;
import com.bytechef.component.definition.Authorization.AuthorizationCallbackResponse;
import com.bytechef.component.definition.Authorization.AuthorizationType;
import com.bytechef.component.definition.Authorization.RefreshTokenResponse;
import com.bytechef.component.definition.Context;
import com.bytechef.platform.component.ComponentConnection;
import com.bytechef.platform.component.domain.ConnectionDefinition;
import com.bytechef.platform.component.domain.OAuth2AuthorizationParameters;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ivica Cardic
 */
public interface ConnectionDefinitionService {

    Map<String, ?> executeAcquire(
        String componentName, int connectionVersion, AuthorizationType authorizationType,
        Map<String, ?> connectionParameters, Context context);

    ApplyResponse executeAuthorizationApply(
        String componentName, int connectionVersion, AuthorizationType authorizationType,
        Map<String, ?> connectionParameters, Context context);

    AuthorizationCallbackResponse executeAuthorizationCallback(
        String componentName, int connectionVersion, AuthorizationType authorizationType,
        Map<String, ?> connectionParameters, Context context, String redirectUri);

    Optional<String> getBaseUri(String componentName, ComponentConnection connection, Context context);

    RefreshTokenResponse executeRefresh(
        String componentName, int connectionVersion, AuthorizationType authorizationType,
        Map<String, ?> connectionParameters, Context context);

    OAuth2AuthorizationParameters getOAuth2AuthorizationParameters(
        String componentName, int connectionVersion, AuthorizationType authorizationType,
        Map<String, ?> connectionParameters, Context context);

    List<String> getAuthorizationDetectOn(
        String componentName, int connectionVersion, AuthorizationType authorizationType);

    List<Object> getAuthorizationRefreshOn(
        String componentName, int connectionVersion, AuthorizationType authorizationType);

    AuthorizationType getAuthorizationType(
        String componentName, int connectionVersion, AuthorizationType authorizationType);

    ConnectionDefinition getConnectionConnectionDefinition(String componentName, int connectionVersion);

    ConnectionDefinition getConnectionDefinition(String componentName, Integer componentVersion);

    List<ConnectionDefinition> getConnectionDefinitions();

    List<ConnectionDefinition> getConnectionDefinitions(String componentName, Integer componentVersion);
}
