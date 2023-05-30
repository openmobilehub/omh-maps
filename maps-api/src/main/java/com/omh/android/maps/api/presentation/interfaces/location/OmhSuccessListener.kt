package com.omh.android.maps.api.presentation.interfaces.location

import com.omh.android.maps.api.presentation.models.OmhCoordinate

/**
 * Abstraction to provide access to callback interface for when a coordinate was obtained.
 */
fun interface OmhSuccessListener {
    /**
     * Completed successfully
     *
     * @param omhCoordinate the coordinate obtained after success.
     */
    fun onSuccess(omhCoordinate: OmhCoordinate)
}
