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

package com.omh.android.maps.api.presentation.interfaces.maps

import com.omh.android.maps.api.presentation.models.OmhCoordinate

/**
 * Abstraction to provide access to [OmhMarker].
 * [OmhMarker] is an icon placed at a particular point on the map's surface.
 */
interface OmhMarker {

    /**
     * The [OmhCoordinate] value for the marker's position on the map.
     *
     * @return [OmhCoordinate] object specifying the marker's current position.
     */
    fun getPosition(): OmhCoordinate

    /**
     * Sets the location of the marker.
     *
     * @param omhCoordinate sets the location.
     */
    fun setPosition(omhCoordinate: OmhCoordinate)

    /**
     * A text string that's displayed in an info window when the user taps the marker.
     *
     * @return a string containing the marker's title.
     */
    fun getTitle(): String?

    /**
     * Sets the title of the marker.
     *
     * @param title sets the title. If null, the title is cleared.
     */
    fun setTitle(title: String?)
}
