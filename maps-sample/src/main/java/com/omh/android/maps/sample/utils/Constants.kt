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

package com.omh.android.maps.sample.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.view.animation.OvershootInterpolator
import com.omh.android.maps.api.presentation.models.OmhCoordinate

object Constants {
    // Coordinate constants
    val PRIME_MERIDIAN: OmhCoordinate = OmhCoordinate(51.4779, -0.0015)
    const val DEFAULT_ZOOM_LEVEL = 15f
    const val ZOOM_LEVEL_5 = 5f

    // Animation constants
    val OVERSHOOT_INTERPOLATOR = OvershootInterpolator()
    const val ANIMATION_DURATION = 250L
    const val INITIAL_TRANSLATION = -75f
    const val FINAL_TRANSLATION = 0f

    // Permissions
    val PERMISSIONS: Array<String> = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

    // Share intent
    const val TYPE_TEXT_PLAIN = "text/plain"
    const val LAT_PARAM = "lat"
    const val LNG_PARAM = "lng"
    const val AUTHORITY_URL = "com.omh.android.maps.sample"
    const val SCHEME_PROTOCOL = "https"
    const val PATH = "maps"

    // Bundle keys
    const val LOCATION_KEY = "location"
    const val ONLY_DISPLAY_KEY = "only display"

    // Loading
    const val SHOW_MESSAGE_TIME = 5000L
}
