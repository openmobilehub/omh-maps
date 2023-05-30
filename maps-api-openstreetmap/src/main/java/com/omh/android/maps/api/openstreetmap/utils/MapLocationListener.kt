package com.omh.android.maps.api.openstreetmap.utils

import android.location.Location
import android.location.LocationListener
import androidx.core.location.LocationListenerCompat

internal class MapLocationListener(val onSuccess: (LocationListener, Location?) -> Unit) :
    LocationListenerCompat {

    override fun onLocationChanged(location: Location) {
        onSuccess(this, location)
    }
}
