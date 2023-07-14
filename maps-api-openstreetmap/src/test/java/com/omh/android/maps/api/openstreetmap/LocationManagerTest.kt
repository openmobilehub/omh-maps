/*
 * Copyright 2023 Open Mobile Hub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omh.android.maps.api.openstreetmap

import android.location.Location
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import com.omh.android.maps.api.openstreetmap.Constants.LESS_ACCURATE
import com.omh.android.maps.api.openstreetmap.Constants.MOST_ACCURATE
import com.omh.android.maps.api.openstreetmap.extensions.getLastKnownLocation
import com.omh.android.maps.api.presentation.models.OmhMapException
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class LocationManagerTest {
    private lateinit var locationManager: LocationManager
    private lateinit var networkLocation: Location
    private lateinit var gpsLocation: Location

    @Before
    fun setUp() {
        locationManager = mockk(relaxed = true)
        networkLocation = mockLocation(LESS_ACCURATE)
        gpsLocation = mockLocation(MOST_ACCURATE)
        every { locationManager.getLastKnownLocation(NETWORK_PROVIDER) } returns networkLocation
        every { locationManager.getLastKnownLocation(GPS_PROVIDER) } returns gpsLocation
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation, then gps location returns`() {
        val result = locationManager.getLastKnownLocation()

        assertEquals(gpsLocation, result)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation with gps null, then a network location returns`() {
        every { locationManager.getLastKnownLocation(GPS_PROVIDER) } returns null
        val result = locationManager.getLastKnownLocation()

        assertEquals(networkLocation, result)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation, then an non gps location returns`() {
        val result = locationManager.getLastKnownLocation()

        assertNotEquals(networkLocation, result)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation and Providers returns null, then returns null`() {
        every { locationManager.getLastKnownLocation(GPS_PROVIDER) } returns null
        every { locationManager.getLastKnownLocation(NETWORK_PROVIDER) } returns null

        val result = locationManager.getLastKnownLocation()

        assertEquals(null, result)
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation without permissions, then throws PermissionError`() {
        val securityException = SecurityException()
        every { locationManager.getLastKnownLocation(any()) } throws securityException

        try {
            locationManager.getLastKnownLocation()
        } catch (exception: OmhMapException.PermissionError) {
            assertTrue(exception is OmhMapException.PermissionError)
        }
    }

    @Test
    fun `given a LocationManager, when getLastKnownLocation with invalid argument, then throws InvalidArgument`() {
        val illegalArgumentException = IllegalArgumentException()
        every { locationManager.getLastKnownLocation(any()) } throws illegalArgumentException

        try {
            locationManager.getLastKnownLocation()
        } catch (exception: OmhMapException.ApiException) {
            assertTrue(exception is OmhMapException.ApiException)
        }
    }

    private fun mockLocation(accuracy: Float): Location {
        val location = mockk<Location>()
        every { location.accuracy } returns accuracy

        return location
    }
}
