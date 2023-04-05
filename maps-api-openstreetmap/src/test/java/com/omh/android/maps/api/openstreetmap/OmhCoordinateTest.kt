package com.omh.android.maps.api.openstreetmap

import com.omh.android.maps.api.openstreetmap.extensions.toGeoPoint
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import org.junit.Assert
import org.junit.Test
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint

class OmhCoordinateTest {
    private val omhCoordinate: OmhCoordinate = OmhCoordinate(16.9, 166.0)

    @Test
    fun `given a IGeoPoint, when toOmhCoordinate, then should return a OmhCoordinate with same Lat and Lng`() {
        val geoPoint: IGeoPoint = omhCoordinate.toGeoPoint()

        Assert.assertEquals(geoPoint.latitude, omhCoordinate.latitude, 0.0)
        Assert.assertEquals(geoPoint.longitude, omhCoordinate.longitude, 0.0)
    }

    @Test
    fun `given a IGeoPoint, when toOmhCoordinate and compare with a different, then should return false`() {
        val geoPoint: IGeoPoint = omhCoordinate.toGeoPoint()
        val anotherGeoPoint = GeoPoint(10.0, 10.0)

        Assert.assertFalse(anotherGeoPoint.latitude == geoPoint.latitude)
        Assert.assertFalse(anotherGeoPoint.longitude == geoPoint.longitude)
    }
}
