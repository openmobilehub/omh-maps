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

package com.omh.android.maps.api.openstreetmap.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.FUSED_PROVIDER
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.openstreetmap.extensions.getLastKnownLocation
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_DISTANCE_M
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_TIME_EXECUTION_MS
import com.omh.android.maps.api.presentation.models.OmhMapException
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.INVALID_LOOPER
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.NULL_LISTENER

internal class LocationProviderClient(context: Context) {
    private var locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    @SuppressWarnings("TooGenericExceptionCaught") // Until find out any specific error.
    fun getCurrentLocation(onSuccess: (Location?) -> Unit, onFailure: (Exception) -> Unit) {
        val providers = listOf(GPS_PROVIDER, NETWORK_PROVIDER, FUSED_PROVIDER)
        val locationListener =
            MapLocationListener { locationListener: LocationListener, location: Location? ->
                handleOnSuccess(locationListener, onSuccess, location)
            }

        try {
            providers.forEach { provider ->
                locationManager.requestLocationUpdates(
                    provider,
                    MIN_TIME_EXECUTION_MS,
                    MIN_DISTANCE_M,
                    locationListener
                )
            }
        } catch (exception: IllegalArgumentException) {
            onFailure(OmhMapException.ApiException(NULL_LISTENER, exception))
        } catch (exception: RuntimeException) {
            onFailure(OmhMapException.ApiException(INVALID_LOOPER, exception))
        } catch (exception: SecurityException) {
            onFailure(OmhMapException.PermissionError(exception))
        } catch (exception: OmhMapException.ApiException) {
            onFailure(exception)
        }
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    @Throws(OmhMapException.ApiException::class)
    private fun handleOnSuccess(
        locationListener: LocationListener,
        onSuccess: (Location?) -> Unit,
        location: Location?
    ) {
        try {
            locationManager.removeUpdates(locationListener)
            onSuccess(location)
        } catch (exception: IllegalArgumentException) {
            throw OmhMapException.ApiException(NULL_LISTENER, exception)
        }
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    fun getLastLocation(onSuccess: (Location?) -> Unit, onFailure: (Exception) -> Unit) {
        try {
            val lastKnownLocation = locationManager.getLastKnownLocation()
            onSuccess(lastKnownLocation)
        } catch (exception: OmhMapException) {
            onFailure(exception)
        }
    }
}
