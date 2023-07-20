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

package com.omh.android.maps.api.openstreetmap

import com.omh.android.maps.api.openstreetmap.Constants.LATITUDE
import com.omh.android.maps.api.openstreetmap.Constants.LONGITUDE
import com.omh.android.maps.api.openstreetmap.Constants.OTHER_LATITUDE
import com.omh.android.maps.api.openstreetmap.Constants.OTHER_LONGITUDE
import com.omh.android.maps.api.openstreetmap.extensions.toOmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint

internal class IGeoPointTest {
    private val geoPoint: IGeoPoint = GeoPoint(LATITUDE, LONGITUDE)

    @Test
    fun `given a IGeoPoint, when toOmhCoordinate, then should return a OmhCoordinate with same Lat and Lng`() {
        val omhCoordinate: OmhCoordinate = geoPoint.toOmhCoordinate()

        assertEquals(geoPoint.latitude, omhCoordinate.latitude, 0.0)
        assertEquals(geoPoint.longitude, omhCoordinate.longitude, 0.0)
    }

    @Test
    fun `given a IGeoPoint, when toOmhCoordinate and compare with a different, then should return false`() {
        val omhCoordinate: OmhCoordinate = geoPoint.toOmhCoordinate()
        val anotherOmhCoordinate = OmhCoordinate(OTHER_LATITUDE, OTHER_LONGITUDE)

        assertFalse(anotherOmhCoordinate.latitude == omhCoordinate.latitude)
        assertFalse(anotherOmhCoordinate.longitude == omhCoordinate.longitude)
    }
}
