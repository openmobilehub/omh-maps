package com.omh.android.maps.api.openstreetmap.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.openstreetmap.extensions.getMostAccurateLastKnownLocation
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_DISTANCE_M
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_TIME_EXECUTION_MS

internal class LocationProviderClient(context: Context) {
    private var locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    @SuppressWarnings("TooGenericExceptionCaught") // Until find out any specific error.
    fun getCurrentLocation(onSuccess: (Location?) -> Unit, onFailure: (Exception) -> Unit) {
        val locationListener =
            MapLocationListener { locationListener: LocationListener, location: Location? ->
                locationManager.removeUpdates(locationListener)
                onSuccess(location)
            }

        try {
            val lastLocation: Location? = locationManager.getMostAccurateLastKnownLocation()
            lastLocation?.let(onSuccess)

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME_EXECUTION_MS,
                MIN_DISTANCE_M,
                locationListener
            )
        } catch (exception: RuntimeException) {
            onFailure(exception)
        }
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    @SuppressWarnings("TooGenericExceptionCaught") // Until find out any specific error.
    fun getLastLocation(onSuccess: (Location?) -> Unit, onFailure: (Exception) -> Unit) {
        try {
            val lastKnownLocation = locationManager.getMostAccurateLastKnownLocation()
            onSuccess(lastKnownLocation)
        } catch (exception: RuntimeException) {
            onFailure(exception)
        }
    }
}
