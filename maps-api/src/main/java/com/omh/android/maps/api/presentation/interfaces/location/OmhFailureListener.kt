package com.omh.android.maps.api.presentation.interfaces.location

/**
 * Abstraction to provide access to callback interface for when a coordinate failed to obtain.
 */
fun interface OmhFailureListener {
    /**
     * Failed with an exception
     *
     * @param exception -> the exception that was thrown.
     */
    fun onFailure(exception: Exception)
}
