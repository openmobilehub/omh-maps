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

package com.omh.android.maps.api.factories

import android.content.Context
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView

/**
 * A Factory to provide any of the interfaces of the OMH Maps Api module.
 * This isn't designed to be used directly from the client side, instead use the [OmhMapProvider]
 */
interface OmhMapFactory {

    /**
     * Provides the [OmhMapView] that is the main entry point with the OMH Maps module.
     *
     * @param context ideally your application context, but an activity context will also work.
     * @return [OmhMapView] instance object.
     */
    fun getOmhMapView(context: Context): OmhMapView

    /**
     * Provides the [OmhLocation] that is the entry point for Locations.
     *
     * @param context ideally your application context, but an activity context will also work.
     * @return [OmhLocation] instance object.
     */
    fun getOmhLocation(context: Context): OmhLocation
}
