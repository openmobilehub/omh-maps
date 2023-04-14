package com.omh.android.maps.api.openstreetmap.presentation.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.openstreetmap.utils.Constants.LOCATION_IS_NULL
import com.omh.android.maps.api.openstreetmap.utils.LocationProviderClient
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener
import com.omh.android.maps.api.presentation.models.OmhCoordinate

class OmhLocationImpl(context: Context) : OmhLocation {
    private val locationProviderClient = LocationProviderClient(context)

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun getCurrentLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        locationProviderClient.getCurrentLocation(
            { location: Location? ->
                if (location != null) {
                    val omhCoordinate = OmhCoordinate(location.latitude, location.longitude)
                    omhOnSuccessListener.onSuccess(omhCoordinate)
                } else {
                    omhOnFailureListener.onFailure(Exception(LOCATION_IS_NULL))
                }
            },
            { exception ->
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
            { location: Location? ->
                if (location != null) {
                    omhOnSuccessListener.onSuccess(OmhCoordinate(location.latitude, location.longitude))
                } else {
                    omhOnFailureListener.onFailure(Exception(LOCATION_IS_NULL))
                }
            },
            { exception ->
                omhOnFailureListener.onFailure(exception)
            }
        )
    }

    internal class Builder : OmhLocation.Builder {
        override fun build(context: Context): OmhLocation {
            return OmhLocationImpl(context)
        }
    }
}
