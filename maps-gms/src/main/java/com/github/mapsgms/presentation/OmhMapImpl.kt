package com.github.mapsgms.presentation

import android.annotation.SuppressLint
import com.github.mapsgms.utils.Converter
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMap
import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.github.openmobilehub.maps.presentation.models.OmhOnCameraIdleListener
import com.github.openmobilehub.maps.presentation.models.OmhOnCameraMoveStartedListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap

class OmhMapImpl(private var googleMap: GoogleMap) : OmhMap {

    override fun getCameraPositionCoordinate(): OmhCoordinate {
        val position = googleMap.cameraPosition.target
        return Converter.convertToCoordinate(position)
    }

    override fun setZoomGesturesEnabled(enableZoomGestures: Boolean) {
        googleMap.uiSettings.isZoomGesturesEnabled = enableZoomGestures
    }

    @SuppressLint("MissingPermission")
    override fun setMyLocationEnabled(enable: Boolean) {
        googleMap.isMyLocationEnabled = true
    }

    override fun setOnCameraMoveStartedListener(listener: OmhOnCameraMoveStartedListener) {
        googleMap.setOnCameraMoveStartedListener {
            listener.onCameraMoveStarted(it)
        }
    }

    override fun setOnCameraIdleListener(listener: OmhOnCameraIdleListener) {
        googleMap.setOnCameraIdleListener {
            listener.onCameraIdle()
        }
    }

    override fun moveCamera(coordinate: OmhCoordinate, zoomLevel: Float) {
        val latLng = Converter.convertToLatLng(coordinate)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
    }
}
