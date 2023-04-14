package com.omh.android.maps.api.openstreetmap

import android.location.Location
import android.location.LocationManager
import com.omh.android.maps.api.openstreetmap.extensions.getAccurateLastKnownLocation
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class LocationManagerTest {
    private lateinit var locationManager: LocationManager

    @Before
    fun setUp() {
        locationManager = mockk(relaxed = true)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation with Providers, then an accurate Location returns`() {
        val locationLessAccurate = mockk<Location>()
        every { locationLessAccurate.accuracy } returns 100f
        val locationMoreAccurate = mockk<Location>()
        every { locationMoreAccurate.accuracy } returns 50f
        every { locationManager.getProviders(true) } returns listOf("provider1", "provider2")
        every { locationManager.getLastKnownLocation("provider1") } returns locationLessAccurate
        every { locationManager.getLastKnownLocation("provider2") } returns locationMoreAccurate

        val result = locationManager.getAccurateLastKnownLocation()

        assertEquals(locationMoreAccurate, result)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation with Providers, then an non accurate Location returns`() {
        val locationLessAccurate = mockk<Location>()
        every { locationLessAccurate.accuracy } returns 100f
        val locationMoreAccurate = mockk<Location>()
        every { locationMoreAccurate.accuracy } returns 50f
        every { locationManager.getProviders(true) } returns listOf("provider1", "provider2")
        every { locationManager.getLastKnownLocation("provider1") } returns locationLessAccurate
        every { locationManager.getLastKnownLocation("provider2") } returns locationMoreAccurate

        val result = locationManager.getAccurateLastKnownLocation()

        assertNotEquals(locationLessAccurate, result)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation with no Providers, then returns null`() {
        every { locationManager.getProviders(true) } returns emptyList()
        every { locationManager.getLastKnownLocation(any()) } returns null

        val result = locationManager.getAccurateLastKnownLocation()

        assertEquals(null, result)
    }
}
