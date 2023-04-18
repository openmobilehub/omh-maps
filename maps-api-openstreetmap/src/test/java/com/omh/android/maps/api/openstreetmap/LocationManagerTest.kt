package com.omh.android.maps.api.openstreetmap

import android.location.Location
import android.location.LocationManager
import com.omh.android.maps.api.openstreetmap.Constants.LESS_ACCURATE
import com.omh.android.maps.api.openstreetmap.Constants.MOST_ACCURATE
import com.omh.android.maps.api.openstreetmap.Constants.PROVIDER_ONE
import com.omh.android.maps.api.openstreetmap.Constants.PROVIDER_TWO
import com.omh.android.maps.api.openstreetmap.extensions.getMostAccurateLastKnownLocation
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class LocationManagerTest {
    private lateinit var locationManager: LocationManager
    private lateinit var locationLessAccurate: Location
    private lateinit var locationMostAccurate: Location

    @Before
    fun setUp() {
        locationManager = mockk(relaxed = true)
        locationLessAccurate = locationLessAccurate()
        locationMostAccurate = locationMostAccurate()
        every { locationManager.getProviders(true) } returns listOf(PROVIDER_ONE, PROVIDER_TWO)
        every { locationManager.getLastKnownLocation(PROVIDER_ONE) } returns locationLessAccurate
        every { locationManager.getLastKnownLocation(PROVIDER_TWO) } returns locationMostAccurate
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation with Providers, then an accurate Location returns`() {
        val result = locationManager.getMostAccurateLastKnownLocation()

        assertEquals(locationMostAccurate, result)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation with Providers, then an non accurate Location returns`() {
        val result = locationManager.getMostAccurateLastKnownLocation()

        assertNotEquals(locationLessAccurate, result)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation with no Providers, then returns null`() {
        every { locationManager.getProviders(true) } returns emptyList()
        every { locationManager.getLastKnownLocation(any()) } returns null

        val result = locationManager.getMostAccurateLastKnownLocation()

        assertEquals(null, result)
    }

    private fun locationMostAccurate(): Location {
        val locationMoreAccurate = mockk<Location>()
        every { locationMoreAccurate.accuracy } returns MOST_ACCURATE

        return locationMoreAccurate
    }

    private fun locationLessAccurate(): Location {
        val locationLessAccurate = mockk<Location>()
        every { locationLessAccurate.accuracy } returns LESS_ACCURATE

        return locationLessAccurate
    }
}
