package com.omh.android.maps.api.presentation.interfaces.maps

/**
 * Abstraction to provide a callback interface for when the My Location button is clicked.
 */
fun interface OmhOnMyLocationButtonClickListener {
    /**
     * Called when the my location button is clicked.
     *
     * @return true if the listener has consumed the event (i.e., the default behavior should not occur);
     * false otherwise (i.e., the default behavior should occur)
     */
    fun onMyLocationButtonClick(): Boolean
}
