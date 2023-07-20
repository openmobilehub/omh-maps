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

package com.omh.android.maps.api.openstreetmap.utils

internal object Constants {

    // Location
    const val MIN_TIME_EXECUTION_MS = 0L
    const val MIN_DISTANCE_M = 0f

    // My Location
    const val PADDING_MY_LOCATION_ICON = 16
    const val DEFAULT_ZOOM_LEVEL = 15.0

    // Gestures and animation
    const val ONE_POINTER = 1
    const val TWO_POINTERS = 2
    const val DELAY_MS = 200L
    const val MIN_DISTANCE = 5

    // Map marker
    const val MIN_ZOOM_LEVEL = 4.0
    const val MAX_ZOOM_LEVEL = 22.0

    // Map Copyright
    const val TEXT_SIZE = 36f
    const val TEXT_COORDINATE_X = 50f
    const val PADDING_TEXT = 20f
    const val TEXT_COPYRIGHT = "@ OpenStreetMap contributors"
}
