package com.github.openmobilehub.maps

import android.os.Parcel
import com.openmobilehub.maps.api.presentation.models.OmhCoordinate
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class OmhCoordinateTest {
    private val latitude = -16.19
    private val longitude = 166.16
    private val omhCoordinate = OmhCoordinate(latitude, longitude)
    private val otherOmhCoordinate = OmhCoordinate(latitude, longitude)

    @Test
    fun `given a lat and lng, when are passed to create a OmhCoordinate, then is created with the lat and lng`() {
        val newOmhCoordinate = OmhCoordinate(latitude, longitude)

        assertEquals(latitude, newOmhCoordinate.latitude, 0.0)
        assertEquals(longitude, newOmhCoordinate.longitude, 0.0)
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
        val expectedStringResult = "lat/lng: ($latitude,$longitude)"
        val stringResult = omhCoordinate.toString()

        assertEquals(expectedStringResult, stringResult)
    }

    @Test
    fun `given a Parcel, when is called toString, then a correct string is returned`() {
        val parcel = mockk<Parcel>()

        every { parcel.writeDouble(any()) } returns Unit
        every { parcel.readDouble() } returns latitude andThen longitude

        omhCoordinate.writeToParcel(parcel, omhCoordinate.describeContents())

        every { parcel.setDataPosition(any()) } returns Unit
        every { parcel.dataPosition() } returns 0

        val createdFromParcel = OmhCoordinate.CREATOR.createFromParcel(parcel)

        assertEquals(omhCoordinate, createdFromParcel)
    }
}
