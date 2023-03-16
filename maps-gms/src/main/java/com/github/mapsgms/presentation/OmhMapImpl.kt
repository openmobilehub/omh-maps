package com.github.mapsgms.presentation

import android.annotation.SuppressLint
import com.github.mapsgms.utils.ConverterUtils
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMap
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

internal class OmhMapImpl(private var googleMap: GoogleMap) : OmhMap {

    override fun getCameraPositionCoordinate(): OmhCoordinate {
        val position: LatLng = googleMap.cameraPosition.target
        return ConverterUtils.convertToOmhCoordinate(position)
    }

    override fun setZoomGesturesEnabled(enableZoomGestures: Boolean) {
        googleMap.uiSettings.isZoomGesturesEnabled = enableZoomGestures
    }

    @SuppressLint("MissingPermission") // TODO handle properly in the corresponding task
    override fun setMyLocationEnabled(enable: Boolean) {
        googleMap.isMyLocationEnabled = enable
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
        val latLng: LatLng = ConverterUtils.convertToLatLng(coordinate)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
    }
}
