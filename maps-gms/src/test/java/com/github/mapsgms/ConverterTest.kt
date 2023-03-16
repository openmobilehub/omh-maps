package com.github.mapsgms

import android.location.Location
import com.github.mapsgms.utils.ConverterUtils
import com.google.android.gms.maps.model.LatLng
import com.openmobilehub.maps.api.presentation.models.OmhCoordinate
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

internal class ConverterTest {
    private val latitude = -16.19
    private val longitude = 166.16
    private val omhCoordinate = OmhCoordinate(latitude, longitude)
    private val latLng = LatLng(latitude, longitude)

    @Test
    fun `when given a OmhCoordinate object a correct LatLng object is converted`() {
        val latLngResult = ConverterUtils.convertToLatLng(omhCoordinate)

        assertTrue(latLng.latitude == latLngResult.latitude && latLng.longitude == latLngResult.longitude)
    }

    @Test
    fun `when given a LatLng object a correct OmhCoordinate object is converted`() {
        val coordinateResult = ConverterUtils.convertToOmhCoordinate(latLng)

        assertTrue(
            omhCoordinate.latitude == coordinateResult.latitude && omhCoordinate.longitude == coordinateResult.longitude
        )
    }

    @Test
    fun `when given a Location object a correct OmhCoordinate object is converted`() {
        val mockLocation: Location = mockk {
            every { latitude } returns -16.19
            every { longitude } returns 166.16
        }
        val coordinateResult = ConverterUtils.convertToOmhCoordinate(mockLocation)

        assertTrue(
            omhCoordinate.latitude == coordinateResult.latitude && omhCoordinate.longitude == coordinateResult.longitude
        )
    }
}
