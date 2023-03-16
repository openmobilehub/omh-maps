package com.openmobilehub.maps.api.presentation.interfaces.maps

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.openmobilehub.maps.api.presentation.models.OmhCoordinate

interface OmhMap {
    fun getCameraPositionCoordinate(): OmhCoordinate

    fun moveCamera(coordinate: OmhCoordinate, zoomLevel: Float)

    fun setZoomGesturesEnabled(enableZoomGestures: Boolean)

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    fun setMyLocationEnabled(enable: Boolean)

    fun setOnCameraMoveStartedListener(listener: OmhOnCameraMoveStartedListener)

    fun setOnCameraIdleListener(listener: OmhOnCameraIdleListener)
}
