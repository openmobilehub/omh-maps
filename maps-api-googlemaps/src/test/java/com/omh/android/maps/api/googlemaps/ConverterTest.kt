package com.omh.android.maps.api.googlemaps

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.omh.android.maps.api.googlemaps.utils.ConverterUtils
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

internal class ConverterTest {
    private val latitude = -16.19
    private val longitude = 166.16
    private val omhCoordinate = OmhCoordinate(latitude, longitude)
    private val latLng = LatLng(latitude, longitude)

    @Test
    fun `given a OmhCoordinate, when is converted to a LatLng, then a correct LatLng is returned`() {
        val latLngResult = ConverterUtils.convertToLatLng(omhCoordinate)

        assertEquals(latLng, latLngResult)
    }

    @Test
    fun `given a LatLng, when is converted to OmhCoordinate, then a correct OmhCoordinate is returned`() {
        val coordinateResult = ConverterUtils.convertToOmhCoordinate(latLng)

        assertEquals(omhCoordinate, coordinateResult)
    }

    @Test
    fun `given a Location, when  is converted to omhCoordinate, then a correct OmhCoordinate is returned`() {
        val mockLocation: Location = mockk {
            every { latitude } returns -16.19
            every { longitude } returns 166.16
        }
        val coordinateResult = ConverterUtils.convertToOmhCoordinate(mockLocation)

        assertEquals(omhCoordinate, coordinateResult)
    }
}
