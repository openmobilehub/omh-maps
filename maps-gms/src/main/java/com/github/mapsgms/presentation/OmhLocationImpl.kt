package com.github.mapsgms.presentation

import android.annotation.SuppressLint
import android.content.Context
import com.github.mapsgms.utils.ConverterUtils
import com.github.mapsgms.utils.LocationUtils
import com.github.openmobilehub.maps.presentation.interfaces.location.OmhFailureListener
import com.github.openmobilehub.maps.presentation.interfaces.location.OmhLocation
import com.github.openmobilehub.maps.presentation.interfaces.location.OmhSuccessListener
import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

private const val LOCATION_IS_NULL = "Location is null"

internal class OmhLocationImpl : OmhLocation {

    @SuppressLint("MissingPermission") // TODO handle properly in the corresponding task
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
                    omhOnSuccessListener.onSuccess(OmhCoordinate(locationResult.latitude, locationResult.longitude))
                } else {
                    omhOnFailureListener.onFailure(Exception(LOCATION_IS_NULL))
                }
            }
            .addOnFailureListener { exception ->
                omhOnFailureListener.onFailure(exception)
            }
    }

    @SuppressLint("MissingPermission") // TODO handle properly in the corresponding task
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
