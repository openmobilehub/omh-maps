package com.omh.android.maps.api.openstreetmap.factories

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.Location
import android.location.LocationListener
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_TIMES_EXECUTION
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_TIME_EXECUTION_MS
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_TIME_MS
import com.omh.android.maps.api.openstreetmap.utils.Constants.TIME_OUT_MS

object LocationListenerFactory {
    fun create(
        handleLocationListener: (LocationListener) -> Unit,
        onSuccess: (Location?) -> Unit
    ): LocationListener {
        var locationUpdates = 0

        return object : LocationListener {
            @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
            override fun onLocationChanged(location: Location) {
                locationUpdates++
                if (numberOfExecutionsExceeded() || timeOutExceeded()) {
                    onSuccess(location)
                    handleLocationListener.invoke(this)
                }
            }

            private fun timeOutExceeded() =
                locationUpdates * MIN_TIME_EXECUTION_MS >= TIME_OUT_MS

            private fun numberOfExecutionsExceeded() =
                MIN_TIME_EXECUTION_MS <= MIN_TIME_MS && locationUpdates >= MIN_TIMES_EXECUTION
        }
    }
}
