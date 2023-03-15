package com.github.openmobilehub.maps.presentation.interfaces.maps

import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.github.openmobilehub.maps.presentation.models.OmhOnCameraIdleListener
import com.github.openmobilehub.maps.presentation.models.OmhOnCameraMoveStartedListener

interface OmhMap {
    fun getCameraPositionCoordinate(): OmhCoordinate

    fun moveCamera(coordinate: OmhCoordinate, zoomLevel: Float)

    fun setZoomGesturesEnabled(enableZoomGestures: Boolean)

    fun setMyLocationEnabled(enable: Boolean)

    fun setOnCameraMoveStartedListener(listener: OmhOnCameraMoveStartedListener)

    fun setOnCameraIdleListener(listener: OmhOnCameraIdleListener)
}
