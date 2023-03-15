package com.github.mapsgms

import com.github.mapsgms.utils.Converter
import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.google.android.gms.maps.model.LatLng
import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterTest {
    @Test
    fun `when given a OmhCoordinate object a correct LatLng object is returned`() {
        val coordinateInput = OmhCoordinate(-16.19, 166.16)
        val expectedLatLng = LatLng(-16.19, 166.16)
        val latLngResult = Converter.convertToLatLng(coordinateInput)

        assertEquals(expectedLatLng, latLngResult)
    }

    @Test
    fun `when given a LatLng object a correct OmhCoordinate object is returned`() {
        val latLngInput = LatLng(-16.19, 166.16)
        val coordinateExpected = OmhCoordinate(-16.19, 166.16)
        val coordinateResult = Converter.convertToCoordinate(latLngInput)

        assertEquals(coordinateExpected, coordinateResult)
    }
}
