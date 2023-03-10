package com.github.mapsgms.presentation

import com.github.mapsgms.utils.Converter
import com.github.openmobilehub.maps.presentation.interfaces.OmhMap
import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap

class OmhMapImpl(private var googleMap: GoogleMap) : OmhMap {

    override fun getCameraPosition(): OmhCoordinate {
        val position = googleMap?.cameraPosition?.target
        return Converter.convertToCoordinate(position)
    }

    override fun setZoomGesturesEnabled(enableZoomGestures: Boolean) {
        googleMap.uiSettings.isZoomGesturesEnabled = enableZoomGestures
    }

    override fun moveCamera(coordinate: OmhCoordinate, zoomLevel: Float) {
        val latLng = Converter.convertToLatLng(coordinate)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
    }
}
