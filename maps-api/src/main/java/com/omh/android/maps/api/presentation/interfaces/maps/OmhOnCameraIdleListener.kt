package com.omh.android.maps.api.presentation.interfaces.maps

/**
 * Abstraction to provide access to callback interface for when camera movement has ended.
 */
fun interface OmhOnCameraIdleListener {
    /**
     * Called when camera movement has ended, there are no pending animations and
     * the user has stopped interacting with the map.
     */
    fun onCameraIdle()
}
