package com.omh.android.maps.api.presentation.interfaces.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import androidx.annotation.RequiresPermission

/**
 * Abstraction to provide access to Location Provider
 */
interface OmhLocation {
    interface Builder {

        fun build(context: Context): OmhLocation
    }

    /**
     * Returns a single location fix representing the best estimate of the current location of the device.
     *
     * @param omhOnSuccessListener -> the callback object that will be triggered when completed successfully.
     * @param omhOnFailureListener -> the callback object that will be triggered when failed.
     */
    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    fun getCurrentLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    )

    /**
     * Returns the most recent historical location currently available.
     * Will return null if no historical location is available. The historical location may be
     * of an arbitrary age, so clients should check how old the location is to see if it suits their purposes.
     *
     * @param omhOnSuccessListener -> the callback object that will be triggered when completed successfully.
     * @param omhOnFailureListener -> the callback object that will be triggered when failed.
     */
    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    fun getLastLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    )
}
