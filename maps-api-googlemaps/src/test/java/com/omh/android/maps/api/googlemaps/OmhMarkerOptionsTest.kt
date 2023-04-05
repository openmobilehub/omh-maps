package com.omh.android.maps.api.googlemaps

import com.omh.android.maps.api.googlemaps.extensions.toMarkerOptions
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

internal class OmhMarkerOptionsTest {
    private val omhCoordinate = OmhCoordinate(16.9, 166.0)
    private val omhMarkerOptions = OmhMarkerOptions().apply { position = omhCoordinate }

    @Test
    fun `given a OmhMarkerOptions, when toMarkerOptions, then MarkerOptions should return with position`() {
        val markerOptions = omhMarkerOptions.toMarkerOptions()

        assertEquals(markerOptions.position.latitude, omhMarkerOptions.position.latitude, 0.0)
        assertEquals(markerOptions.position.longitude, omhMarkerOptions.position.longitude, 0.0)
    }

    @Test
    fun `given a OmhMarkerOptions, when toMarkerOptions and compare with a different, then should return false`() {
        val markerOptions = omhMarkerOptions.toMarkerOptions()
        val anotherMarkerOptions = OmhMarkerOptions().apply {
            position = OmhCoordinate(10.0, 10.0)
        }

        assertFalse(markerOptions.position.latitude == anotherMarkerOptions.position.latitude)
        assertFalse(markerOptions.position.longitude == anotherMarkerOptions.position.longitude)
    }
}
