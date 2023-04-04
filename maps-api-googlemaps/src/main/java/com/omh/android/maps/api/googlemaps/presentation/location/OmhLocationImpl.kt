package com.omh.android.maps.api.googlemaps.presentation.location

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.omh.android.maps.api.googlemaps.utils.Constants.LOCATION_IS_NULL
import com.omh.android.maps.api.googlemaps.utils.ConverterUtils
import com.omh.android.maps.api.googlemaps.utils.LocationUtils
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener
import com.omh.android.maps.api.presentation.models.OmhCoordinate

internal class OmhLocationImpl : OmhLocation {

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    override fun getCurrentLocation(
        context: Context,
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        val locationClient = LocationServices.getFusedLocationProviderClient(context)

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
                    omhOnFailureListener.onFailure(Exception(LOCATION_IS_NULL))
                }
            }
            .addOnFailureListener { exception ->
                omhOnFailureListener.onFailure(exception)
            }
    }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    override fun getLastLocation(
        context: Context,
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        val locationClient = LocationServices.getFusedLocationProviderClient(context)

        locationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    omhOnSuccessListener.onSuccess(ConverterUtils.convertToOmhCoordinate(location))
                } else {
                    omhOnFailureListener.onFailure(Exception(LOCATION_IS_NULL))
                }
            }
            .addOnFailureListener { exception ->
                omhOnFailureListener.onFailure(exception)
            }
    }
}
