package com.github.openmobilehub.maps.presentation.interfaces

import com.github.openmobilehub.maps.presentation.models.OmhCoordinate

interface OmhMap {
    fun getCameraPosition(): OmhCoordinate
    fun setZoomGesturesEnabled(enableZoomGestures: Boolean)
    fun moveCamera(coordinate: OmhCoordinate, zoomLevel: Float)
}
