package com.omh.android.maps.api.openstreetmap

import com.omh.android.maps.api.openstreetmap.extensions.toOmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint

class IGeoPointTest {
    private val geoPoint: IGeoPoint = GeoPoint(16.9, 166.0)

    @Test
    fun `given a IGeoPoint, when toOmhCoordinate, then should return a OmhCoordinate with same Lat and Lng`() {
        val omhCoordinate: OmhCoordinate = geoPoint.toOmhCoordinate()

        assertEquals(geoPoint.latitude, omhCoordinate.latitude, 0.0)
        assertEquals(geoPoint.longitude, omhCoordinate.longitude, 0.0)
    }

    @Test
    fun `given a IGeoPoint, when toOmhCoordinate and compare with a different, then should return false`() {
        val omhCoordinate: OmhCoordinate = geoPoint.toOmhCoordinate()
        val anotherOmhCoordinate = OmhCoordinate(10.0, 10.0)

        assertFalse(anotherOmhCoordinate.latitude == omhCoordinate.latitude)
        assertFalse(anotherOmhCoordinate.longitude == omhCoordinate.longitude)
    }
}
