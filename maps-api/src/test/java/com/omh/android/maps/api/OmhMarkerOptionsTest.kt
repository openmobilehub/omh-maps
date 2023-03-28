package com.omh.android.maps.api

import android.os.Parcel
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

internal class OmhMarkerOptionsTest {
    private val latitude = -16.19
    private val longitude = 166.16
    private val omhCoordinate = OmhCoordinate(latitude, longitude)
    private val omhMarkerOptions = OmhMarkerOptions().apply { position = omhCoordinate }

    @Test
    fun `given a OmhCoordinate, when is assigned in a OmhMarkerOptions, then is assigned the OmhCoordinate`() {
        val newOmhMarkerOptions = OmhMarkerOptions().apply { position = omhCoordinate }

        assertEquals(newOmhMarkerOptions.position, omhCoordinate)
    }

    @Test
    fun `given a OmhMarkerOptions, when compare to another with same OmhCoordinate, then is true`() {
        val newOmhMarkerOptions = OmhMarkerOptions().apply { position = omhCoordinate }

        assertEquals(newOmhMarkerOptions.position, omhMarkerOptions.position)
    }

    @Test
    fun `given a OmhMarkerOptions, when compared to another with a different OmhCoordinate, then is false`() {
        val compareOmhCoordinate = OmhCoordinate()
        val compareOmhMarkerOptions = OmhMarkerOptions().apply { position = compareOmhCoordinate }

        assertFalse(omhMarkerOptions.position == compareOmhMarkerOptions.position)
    }

    @Test
    fun `given a Parcel, when createFromParcel and compared to another with same OmhCoordinate, then is true`() {
        val parcel = mockk<Parcel>()

        every { parcel.writeParcelable(any(), any()) } returns Unit
        every {
            parcel.readParcelable<OmhCoordinate>(OmhCoordinate::class.java.classLoader)
        } returns omhMarkerOptions.position
        omhMarkerOptions.writeToParcel(parcel, omhMarkerOptions.describeContents())

        every { parcel.setDataPosition(any()) } returns Unit
        every { parcel.dataPosition() } returns 0

        val createdFromParcel = OmhMarkerOptions.CREATOR.createFromParcel(parcel)

        assertEquals(omhMarkerOptions.position, createdFromParcel.position)
    }

    @Test
    fun `given a Parcel, when createFromParcel and compared to another with different OmhCoordinate, then is false`() {
        val compareOmhCoordinate = OmhCoordinate()
        val compareOmhMarkerOptions = OmhMarkerOptions().apply { position = compareOmhCoordinate }
        val parcel = mockk<Parcel>()

        every { parcel.writeParcelable(any(), any()) } returns Unit
        every {
            parcel.readParcelable<OmhCoordinate>(OmhCoordinate::class.java.classLoader)
        } returns omhMarkerOptions.position
        omhMarkerOptions.writeToParcel(parcel, omhMarkerOptions.describeContents())

        every { parcel.setDataPosition(any()) } returns Unit
        every { parcel.dataPosition() } returns 0

        val createdFromParcel = OmhMarkerOptions.CREATOR.createFromParcel(parcel)

        assertFalse(compareOmhMarkerOptions.position == createdFromParcel.position)
    }
}
