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

package com.bytechef.component.retable.util;

import static com.bytechef.component.definition.ComponentDsl.bool;
import static com.bytechef.component.definition.ComponentDsl.dateTime;
import static com.bytechef.component.definition.ComponentDsl.fileEntry;
import static com.bytechef.component.definition.ComponentDsl.integer;
import static com.bytechef.component.definition.ComponentDsl.number;
import static com.bytechef.component.definition.ComponentDsl.string;
import static com.bytechef.component.definition.Context.Http.ResponseType;
import static com.bytechef.component.definition.Context.Http.responseType;
import static com.bytechef.component.retable.constant.RetableConstants.COLUMNS;
import static com.bytechef.component.retable.constant.RetableConstants.COLUMN_ID;
import static com.bytechef.component.retable.constant.RetableConstants.DATA;
import static com.bytechef.component.retable.constant.RetableConstants.RETABLE_ID;

import com.bytechef.component.definition.ActionContext;
import com.bytechef.component.definition.ComponentDsl.ModifiableValueProperty;
import com.bytechef.component.definition.Parameters;
import com.bytechef.component.definition.Property.ControlType;
import com.bytechef.component.definition.Property.ValueProperty;
import com.bytechef.component.definition.TypeReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marija Horvat
 */
public class RetablePropertiesUtils {

    private RetablePropertiesUtils() {
    }

    public static List<ValueProperty<?>> createPropertiesForRowValues(
        Parameters inputParameters, Parameters connectionParameters, Map<String, String> lookupDependsOnPaths,
        ActionContext actionContext) {

        Map<String, Map<String, Object>> body = actionContext
            .http(http -> http.get("/retable/" + inputParameters.getRequiredString(RETABLE_ID)))
            .configuration(responseType(ResponseType.JSON))
            .execute()
            .getBody(new TypeReference<>() {});

        Map<String, Object> data = body.get(DATA);

        if (data.get(COLUMNS) instanceof List<?> items) {
            return new ArrayList<>(items.stream()
                .filter(o -> o instanceof Map<?, ?>)
                .map(o -> createProperty((Map<?, ?>) o))
                .filter(Objects::nonNull)
                .toList());
        }

        return List.of();
    }

    private static ModifiableValueProperty<?, ?> createProperty(Map<?, ?> columnMap) {
        String columnId = (String) columnMap.get(COLUMN_ID);
        String title = (String) columnMap.get("title");
        String type = (String) columnMap.get("type");

        if (type != null) {
            return switch (type) {
                case "text", "select", "color", "phone_number" -> string(columnId)
                    .label(title)
                    .required(false);
                case "email" -> string(columnId)
                    .label(title)
                    .required(false)
                    .controlType(ControlType.EMAIL);
                case "checkbox" -> bool(columnId)
                    .label(title)
                    .required(false);
                case "number", "percent", "currency" -> number(columnId)
                    .label(title)
                    .required(false);
                case "rating", "duration" -> integer(columnId)
                    .label(title)
                    .required(false);
                case "calendar" -> dateTime(columnId)
                    .label(title)
                    .required(false);
                case "attachment", "image" -> fileEntry(columnId)
                    .label(title)
                    .required(false);
                default -> null;
            };
        }

        return null;
    }
}
