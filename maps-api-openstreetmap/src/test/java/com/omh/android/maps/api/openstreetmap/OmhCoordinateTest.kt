package com.omh.android.maps.api.openstreetmap

import com.omh.android.maps.api.openstreetmap.extensions.toGeoPoint
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint

class OmhCoordinateTest {
    private val omhCoordinate: OmhCoordinate = OmhCoordinate(16.9, 166.0)

    @Test
    fun `given a IGeoPoint, when toOmhCoordinate, then should return a OmhCoordinate with same Lat and Lng`() {
        val geoPoint: IGeoPoint = omhCoordinate.toGeoPoint()

        assertEquals(geoPoint.latitude, omhCoordinate.latitude, 0.0)
        assertEquals(geoPoint.longitude, omhCoordinate.longitude, 0.0)
    }

    @Test
    fun `given a IGeoPoint, when toOmhCoordinate and compare with a different, then should return false`() {
        val geoPoint: IGeoPoint = omhCoordinate.toGeoPoint()
        val anotherGeoPoint = GeoPoint(10.0, 10.0)

        assertFalse(anotherGeoPoint.latitude == geoPoint.latitude)
        assertFalse(anotherGeoPoint.longitude == geoPoint.longitude)
    }
}
