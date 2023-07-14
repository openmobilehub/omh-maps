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

package com.omh.android.maps.api.googlemaps.presentation.maps

import com.google.android.gms.maps.model.Marker
import com.omh.android.maps.api.googlemaps.utils.ConverterUtils
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMarker
import com.omh.android.maps.api.presentation.models.OmhCoordinate

internal class OmhMarkerImpl(private val marker: Marker) : OmhMarker {

    override fun getPosition(): OmhCoordinate {
        return ConverterUtils.convertToOmhCoordinate(marker.position)
    }

    override fun setPosition(omhCoordinate: OmhCoordinate) {
        marker.position = ConverterUtils.convertToLatLng(omhCoordinate)
    }

    override fun getTitle(): String? {
        return marker.title
    }

    override fun setTitle(title: String?) {
        marker.title = title
    }
}
