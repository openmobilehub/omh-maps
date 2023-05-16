package com.omh.android.maps.api.openstreetmap.extensions

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.Location
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.presentation.models.OmhMapException
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.INVALID_PROVIDER

@RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
internal fun LocationManager.getLastKnownLocation(
    providers: List<String> = listOf(GPS_PROVIDER, NETWORK_PROVIDER)
): Location? {
    for (provider in providers) {
        val location = this.obtainLastKnownLocation(provider)
        if (location != null) return location
    }

    return null
}

@RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
@Throws(OmhMapException::class)
private fun LocationManager.obtainLastKnownLocation(provider: String): Location? {
    return try {
        getLastKnownLocation(provider)
    } catch (exception: SecurityException) {
        throw OmhMapException.PermissionError(exception)
    } catch (exception: IllegalArgumentException) {
        throw OmhMapException.ApiException(INVALID_PROVIDER, exception)
    }
}
