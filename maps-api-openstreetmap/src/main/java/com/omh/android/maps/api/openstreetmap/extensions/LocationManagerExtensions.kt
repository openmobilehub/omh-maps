package com.omh.android.maps.api.openstreetmap.extensions

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.Criteria
import android.location.Criteria.ACCURACY_FINE
import android.location.Criteria.POWER_HIGH
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission

@RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
internal fun LocationManager.getMostAccurateLastKnownLocation(): Location? {
    var accurateLocation: Location? = null
    val criteria = Criteria().apply {
        accuracy = ACCURACY_FINE
        powerRequirement = POWER_HIGH
    }
    val provider = getBestProvider(criteria, true)

    provider
        ?.let(::getLastKnownLocation)
        ?.let { lastKnownLocation: Location? -> accurateLocation = lastKnownLocation }

    return accurateLocation
}
