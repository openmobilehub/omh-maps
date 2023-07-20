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
 * Abstraction to provide a callback interface for when the My Location button is clicked.
 */
fun interface OmhOnMyLocationButtonClickListener {
    /**
     * Called when the my location button is clicked.
     *
     * @return true if the listener has consumed the event (i.e., the default behavior should not occur);
     * false otherwise (i.e., the default behavior should occur)
     */
    fun onMyLocationButtonClick(): Boolean
}
