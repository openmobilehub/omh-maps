package com.omh.android.maps.api.openstreetmap.extensions

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission

@RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
fun LocationManager.getMostAccurateLastKnownLocation(): Location? {
    val providers = getProviders(true)
    var accurateLocation: Location? = null

    for (provider in providers) {
        getLastKnownLocation(provider)?.let { lastKnownLocation: Location? ->
            if (lastKnownLocation.isMoreAccurateThan(accurateLocation)) {
                accurateLocation = lastKnownLocation
            }
        }
    }

    return accurateLocation
}