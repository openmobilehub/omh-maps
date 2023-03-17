package com.openmobilehub.maps.api.presentation.interfaces.maps

/**
 * Abstraction to provide access to Callback interface for when the map is ready to be used.
 */
fun interface OmhOnMapReadyCallback {
    /**
     * Called when the map is ready to be used.
     *
     * @param omhMap -> A instance of a OmhMap associated with the OmhMapView that defines the callback.
     */
    fun onMapReady(omhMap: OmhMap)
}
