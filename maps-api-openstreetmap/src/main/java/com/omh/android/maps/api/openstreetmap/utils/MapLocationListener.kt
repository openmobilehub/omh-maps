package com.omh.android.maps.api.openstreetmap.utils

import android.location.Location
import android.location.LocationListener
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_TIMES_EXECUTION
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_TIME_EXECUTION_MS
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_TIME_MS
import com.omh.android.maps.api.openstreetmap.utils.Constants.TIME_OUT_MS

internal class MapLocationListener(val onSuccess: (LocationListener, Location?) -> Unit) : LocationListener {
    private var locationUpdates = 0
    override fun onLocationChanged(location: Location) {
        locationUpdates++
        if (numberOfExecutionsExceeded() || timeOutExceeded()) {
            onSuccess(this, location)
        }
    }

    private fun timeOutExceeded() =
        locationUpdates * MIN_TIME_EXECUTION_MS >= TIME_OUT_MS

    private fun numberOfExecutionsExceeded() =
        MIN_TIME_EXECUTION_MS <= MIN_TIME_MS && locationUpdates >= MIN_TIMES_EXECUTION
}
