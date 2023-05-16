package com.omh.android.maps.api.googlemaps.presentation.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.omh.android.maps.api.googlemaps.utils.ConverterUtils
import com.omh.android.maps.api.googlemaps.utils.LocationUtils
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMapException
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.NULL_LOCATION
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.getStatusCodeString

internal class OmhLocationImpl(context: Context) : OmhLocation {

    private val locationClient: FusedLocationProviderClient

    init {
        locationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun getCurrentLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        locationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            LocationUtils.createCancellationToken()
        )
            .addOnSuccessListener { locationResult ->
                if (locationResult != null) {
                    omhOnSuccessListener.onSuccess(
                        OmhCoordinate(
                            locationResult.latitude,
                            locationResult.longitude
                        )
                    )
                } else {
                    omhOnFailureListener.onFailure(
                        exception = OmhMapException.NullLocation(
                            cause = IllegalStateException(getStatusCodeString(NULL_LOCATION))
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                omhOnFailureListener.onFailure(exception)
            }
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun getLastLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        locationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    omhOnSuccessListener.onSuccess(ConverterUtils.convertToOmhCoordinate(location))
                } else {
                    omhOnFailureListener.onFailure(
                        exception = OmhMapException.NullLocation(
                            cause = IllegalStateException(getStatusCodeString(NULL_LOCATION))
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                omhOnFailureListener.onFailure(exception)
            }
    }

    internal class Builder : OmhLocation.Builder {
        override fun build(context: Context): OmhLocation {
            return OmhLocationImpl(context)
        }
    }
}
