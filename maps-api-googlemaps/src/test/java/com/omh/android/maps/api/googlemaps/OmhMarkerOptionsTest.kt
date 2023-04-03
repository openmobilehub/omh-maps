package com.omh.android.maps.api.googlemaps

import com.omh.android.maps.api.googlemaps.extensions.toMarkerOptions
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions
import org.junit.Assert.assertEquals
import org.junit.Test

internal class OmhMarkerOptionsTest {
    @Test
    fun `given a OmhMarkerOptions, when toMarkerOptions, then it should have a correct position`() {
        val omhCoordinate = OmhCoordinate(16.9, 166.0)
        val omhMarkerOptions = OmhMarkerOptions().apply { position = omhCoordinate }
        val markerOptions = omhMarkerOptions.toMarkerOptions()

        assertEquals(markerOptions.position.latitude, omhMarkerOptions.position.latitude, 0.0)
        assertEquals(markerOptions.position.longitude, omhMarkerOptions.position.longitude, 0.0)
    }
}
