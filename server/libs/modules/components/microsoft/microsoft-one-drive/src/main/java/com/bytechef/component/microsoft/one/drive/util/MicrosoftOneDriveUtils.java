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

package com.bytechef.component.microsoft.one.drive.util;

import static com.bytechef.component.definition.ComponentDsl.option;
import static com.bytechef.component.microsoft.one.drive.constant.MicrosoftOneDriveConstants.FILE;
import static com.bytechef.component.microsoft.one.drive.constant.MicrosoftOneDriveConstants.ID;
import static com.bytechef.component.microsoft.one.drive.constant.MicrosoftOneDriveConstants.NAME;
import static com.bytechef.component.microsoft.one.drive.constant.MicrosoftOneDriveConstants.VALUE;
import static com.bytechef.microsoft.commons.MicrosoftUtils.ODATA_NEXT_LINK;
import static com.bytechef.microsoft.commons.MicrosoftUtils.getItemsFromNextPage;

import com.bytechef.component.definition.ActionContext;
import com.bytechef.component.definition.Context;
import com.bytechef.component.definition.Context.Http;
import com.bytechef.component.definition.Option;
import com.bytechef.component.definition.Parameters;
import com.bytechef.component.definition.TypeReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Monika Domiter
 */
public class MicrosoftOneDriveUtils {

    private MicrosoftOneDriveUtils() {
    }

    public static List<Option<String>> getFileIdOptions(
        Parameters inputParameters, Parameters connectionParameters, Map<String, String> dependencyPaths,
        String searchText, ActionContext actionContext) {

        Map<String, Object> body = actionContext.http(http -> http.get("/me/drive/root/search"))
            .configuration(Http.responseType(Http.ResponseType.JSON))
            .execute()
            .getBody(new TypeReference<>() {});

        List<Option<String>> options = new ArrayList<>();

        if (body.get(VALUE) instanceof List<?> list) {
            for (Object item : list) {
                if (item instanceof Map<?, ?> map && map.containsKey(FILE)) {
                    options.add(option((String) map.get(NAME), (String) map.get(ID)));
                }
            }
        }

        List<Map<?, ?>> itemsFromNextPage = getItemsFromNextPage((String) body.get(ODATA_NEXT_LINK), actionContext);

        for (Map<?, ?> map : itemsFromNextPage) {
            if (map.containsKey(FILE)) {
                options.add(option((String) map.get(NAME), (String) map.get(ID)));
            }
        }

        return options;
    }

    public static String getFolderId(String parentId) {
        return (parentId == null || parentId.isEmpty()) ? "root" : parentId;
    }

    public static List<Option<String>> getFolderIdOptions(
        Parameters inputParameters, Parameters connectionParameters, Map<String, String> dependencyPaths,
        String searchText, Context context) {

        Map<String, Object> body = context.http(http -> http.get("/me/drive/root/search"))
            .configuration(Http.responseType(Http.ResponseType.JSON))
            .execute()
            .getBody(new TypeReference<>() {});

        List<Option<String>> options = new ArrayList<>();

        if (body.get(VALUE) instanceof List<?> list) {
            for (Object item : list) {
                if (item instanceof Map<?, ?> map && map.containsKey("folder")) {
                    options.add(option((String) map.get(NAME), (String) map.get(ID)));
                }
            }
        }

        List<Map<?, ?>> itemsFromNextPage = getItemsFromNextPage((String) body.get(ODATA_NEXT_LINK), context);

        for (Map<?, ?> map : itemsFromNextPage) {
            if (map.containsKey("folder")) {
                options.add(option((String) map.get(NAME), (String) map.get(ID)));
            }
        }

        return options;
    }

}
