package com.omh.android.maps.api.openstreetmap.presentation.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission

class LocationProviderClient(context: Context) {

    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    fun getCurrentLocation(callback: (Location?) -> Unit) {
        locationManager.requestSingleUpdate(
            LocationManager.GPS_PROVIDER,
            { location -> callback(location) },
            null
        )
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    fun getLastLocation(callback: (Location?) -> Unit) {
        val lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        callback(lastKnownLocation)
    }
}
