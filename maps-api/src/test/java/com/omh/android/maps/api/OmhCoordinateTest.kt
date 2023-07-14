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

package com.omh.android.maps.api

import android.os.Parcel
import com.omh.android.maps.api.Constants.LATITUDE
import com.omh.android.maps.api.Constants.LONGITUDE
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class OmhCoordinateTest {
    private val omhCoordinate = OmhCoordinate(LATITUDE, LONGITUDE)
    private val otherOmhCoordinate = OmhCoordinate(LATITUDE, LONGITUDE)

    @Test
    fun `given a lat and lng, when are passed to create a OmhCoordinate, then is created with the lat and lng`() {
        val newOmhCoordinate = OmhCoordinate(LATITUDE, LONGITUDE)

        assertEquals(LATITUDE, newOmhCoordinate.latitude, 0.0)
        assertEquals(LONGITUDE, newOmhCoordinate.longitude, 0.0)
    }

    @Test
    fun `given a OmhCoordinate, when is compared to another with same lat and lng, then true is returned`() {
        assertTrue(omhCoordinate == otherOmhCoordinate)
    }

    @Test
    fun `given a OmhCoordinate, when is compared to another with a different lat and lng, then false is returned`() {
        val compareOmhCoordinate = OmhCoordinate()

        assertFalse(omhCoordinate == compareOmhCoordinate)
    }

    @Test
    fun `given a OmhCoordinate, when hashCode is compared to another with same lat and lng, then true is returned`() {
        assertEquals(omhCoordinate.hashCode(), otherOmhCoordinate.hashCode())
    }

    @Test
    fun `given a OmhCoordinate, when is called toString, then a correct string is returned`() {
        val expectedStringResult = "lat/lng: ($LATITUDE,$LONGITUDE)"
        val stringResult = omhCoordinate.toString()

        assertEquals(expectedStringResult, stringResult)
    }

    @Test
    fun `given a Parcel, when createFromParcel and compared to original OmhCoordinate, then true is returned`() {
        val parcel = mockk<Parcel>()

        every { parcel.writeDouble(any()) } returns Unit
        every { parcel.readDouble() } returns LATITUDE andThen LONGITUDE

        omhCoordinate.writeToParcel(parcel, omhCoordinate.describeContents())

        every { parcel.setDataPosition(any()) } returns Unit
        every { parcel.dataPosition() } returns 0

        val createdFromParcel: OmhCoordinate = OmhCoordinate.CREATOR.createFromParcel(parcel)

        assertEquals(omhCoordinate, createdFromParcel)
    }

    @Test
    fun `given a Parcel, when createFromParcel and compared to different OmhCoordinate, then false is returned`() {
        val compareOmhCoordinate = OmhCoordinate()
        val parcel = mockk<Parcel>()

        every { parcel.writeDouble(any()) } returns Unit
        every { parcel.readDouble() } returns LATITUDE andThen LONGITUDE

        omhCoordinate.writeToParcel(parcel, omhCoordinate.describeContents())

        every { parcel.setDataPosition(any()) } returns Unit
        every { parcel.dataPosition() } returns 0

        val createdFromParcel: OmhCoordinate = OmhCoordinate.CREATOR.createFromParcel(parcel)

        assertFalse(compareOmhCoordinate == createdFromParcel)
    }
}
