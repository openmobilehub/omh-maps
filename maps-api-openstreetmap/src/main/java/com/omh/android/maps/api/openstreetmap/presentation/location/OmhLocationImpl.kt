package com.omh.android.maps.api.openstreetmap.presentation.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.openstreetmap.extensions.toOmhCoordinate
import com.omh.android.maps.api.openstreetmap.utils.Constants.LOCATION_IS_NULL
import com.omh.android.maps.api.openstreetmap.utils.LocationProviderClient
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener

internal class OmhLocationImpl(context: Context) : OmhLocation {
    private val locationProviderClient = LocationProviderClient(context)

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun getCurrentLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        locationProviderClient.getCurrentLocation(
            onSuccess = { location: Location? ->
                handleOnSuccess(location, omhOnFailureListener, omhOnSuccessListener)
            },
            onFailure = { exception: Exception ->
                omhOnFailureListener.onFailure(exception)
            }
        )
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun getLastLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        locationProviderClient.getLastLocation(
            onSuccess = { location: Location? ->
                handleOnSuccess(location, omhOnFailureListener, omhOnSuccessListener)
            },
            onFailure = { exception: Exception ->
                omhOnFailureListener.onFailure(exception)
            }
        )
    }

    private fun handleOnSuccess(
        location: Location?,
        omhOnFailureListener: OmhFailureListener,
        omhOnSuccessListener: OmhSuccessListener
    ) {
        if (location == null) {
            omhOnFailureListener.onFailure(Exception(LOCATION_IS_NULL))
        } else {
            omhOnSuccessListener.onSuccess(location.toOmhCoordinate())
        }
    }

    internal class Builder : OmhLocation.Builder {
        override fun build(context: Context): OmhLocation {
            return OmhLocationImpl(context)
        }
    }
}
