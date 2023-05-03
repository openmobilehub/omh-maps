package com.omh.android.maps.api.googlemaps.presentation.maps

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.omh.android.maps.api.googlemaps.extensions.toMarkerOptions
import com.omh.android.maps.api.googlemaps.utils.ConverterUtils
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMarker
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMyLocationButtonClickListener
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions

internal class OmhMapImpl(private var googleMap: GoogleMap) : OmhMap {
    override fun addMarker(options: OmhMarkerOptions): OmhMarker? {
        val googleOptions = options.toMarkerOptions()
        val marker: Marker? = googleMap.addMarker(googleOptions)

        return marker?.let { OmhMarkerImpl(it) }
    }

    override fun getCameraPositionCoordinate(): OmhCoordinate {
        val position: LatLng = googleMap.cameraPosition.target
        return ConverterUtils.convertToOmhCoordinate(position)
    }

    override fun setZoomGesturesEnabled(enableZoomGestures: Boolean) {
        googleMap.uiSettings.isZoomGesturesEnabled = enableZoomGestures
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun setMyLocationEnabled(enable: Boolean) {
        googleMap.isMyLocationEnabled = enable
    }

    override fun isMyLocationEnabled(): Boolean {
        return googleMap.isMyLocationEnabled
    }

    override fun setMyLocationButtonClickListener(
        omhOnMyLocationButtonClickListener: OmhOnMyLocationButtonClickListener
    ) {
        googleMap.setOnMyLocationButtonClickListener {
            omhOnMyLocationButtonClickListener.onMyLocationButtonClick()
        }
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
