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

/**
 * Callback interface for when the map has finished rendering.
 * This occurs after all tiles required to render the map have been fetched,
 * and all labeling is complete. This event will not fire if the map never loads due to connectivity
 * issues, or if the map is continuously changing and never completes loading due to the user
 * constantly interacting with the map.
 */
fun interface OmhMapLoadedCallback {
    /**
     * Called when camera movement has ended, there are no pending animations and the user has
     * stopped interacting with the map.
     */
    fun onMapLoaded()
}
