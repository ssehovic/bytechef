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

package com.bytechef.component.definition;

import java.util.List;
import java.util.Optional;

/**
 * Used for specifying a connection.
 *
 * @author Ivica Cardic
 */
public interface ConnectionDefinition {

    /**
     *
     */
    String BASE_URI = "baseUri";

    /**
     *
     * @return
     */
    Optional<Boolean> getAuthorizationRequired();

    /**
     *
     * @return
     */
    Optional<List<? extends Authorization>> getAuthorizations();

    /**
     *
     * @return
     */
    Optional<ParameterValueGetter> getBaseUriParameterValueGetter();

    /**
     *
     * @return
     */
    Optional<List<? extends Property>> getProperties();

    /**
     * TODO
     *
     * @return
     */
    Optional<TestConsumer> getTest();

    /**
     *
     * @return
     */
    int getVersion();

    /**
     *
     */
    @FunctionalInterface
    interface ParameterValueGetter {

        /**
         * @param connectionParameters
         * @param context
         * @return
         */
        String from(Parameters connectionParameters, Context context);
    }

    /**
     *
     */
    @FunctionalInterface
    interface TestConsumer {

        /**
         * @param connectionParameters
         * @param context
         */
        void accept(Parameters connectionParameters, Context context);
    }
}
