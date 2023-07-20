/*
 * Copyright 2023 Open Mobile Hub
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

package com.omh.android.maps.api.presentation.models

object OmhMapStatusCodes {

    const val LOCATION_ERROR = 1
    const val DEVELOPER_ERROR = 2
    const val INTERNAL_ERROR = 3
    const val INVALID_PERMISSION = 5
    const val INVALID_PROVIDER = 7
    const val NULL_LISTENER = 8
    const val INVALID_LOOPER = 9
    const val NULL_LOCATION = 10

    fun getStatusCodeString(code: Int): String {
        return when (code) {
            LOCATION_ERROR -> "Location was not able to get"
            DEVELOPER_ERROR -> "DEVELOPER_ERROR"
            INTERNAL_ERROR -> "INTERNAL ERROR"
            INVALID_PERMISSION -> "No suitable permission is present"
            INVALID_PROVIDER -> "Provider is null or doesn't exist"
            NULL_LISTENER -> "Listener is null"
            INVALID_LOOPER -> "Calling thread has no Looper"
            NULL_LOCATION -> "Location is null"
            else -> "Unknown status code: $code"
        }
    }
}
