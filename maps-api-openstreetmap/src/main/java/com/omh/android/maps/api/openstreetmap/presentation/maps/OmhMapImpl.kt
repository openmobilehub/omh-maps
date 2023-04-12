package com.omh.android.maps.api.openstreetmap.presentation.maps

import com.omh.android.maps.api.openstreetmap.extensions.toGeoPoint
import com.omh.android.maps.api.openstreetmap.extensions.toOmhCoordinate
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMyLocationButtonClickListener
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions
import org.osmdroid.api.IGeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class OmhMapImpl(private var mapView: MapView) : OmhMap {

    private var isMyLocationEnabled = false

    override fun addMarker(options: OmhMarkerOptions) {
        Marker(mapView).apply {
            position = options.position.toGeoPoint()
        }
    }

    override fun getCameraPositionCoordinate(): OmhCoordinate {
        val centerPosition: IGeoPoint = mapView.mapCenter
        return centerPosition.toOmhCoordinate()
    }

    override fun moveCamera(coordinate: OmhCoordinate, zoomLevel: Float) {
        with(mapView.controller) {
            val geoPoint: IGeoPoint = coordinate.toGeoPoint()
            setCenter(geoPoint)
            setZoom(zoomLevel.toDouble())
        }
    }

    override fun setZoomGesturesEnabled(enableZoomGestures: Boolean) {
        mapView.setMultiTouchControls(enableZoomGestures)
        // Todo Enable or disable missing gestures in the corresponding task.
    }

    override fun setMyLocationEnabled(enable: Boolean) {
        isMyLocationEnabled = enable
        // Todo Enable or disable MyLocation in the corresponding task.
    }

    override fun isMyLocationEnabled(): Boolean {
        return isMyLocationEnabled
    }

    override fun setMyLocationButtonClickListener(
        omhOnMyLocationButtonClickListener: OmhOnMyLocationButtonClickListener
    ) {
        // Todo Add listener when MyLocation is enabled in the corresponding task.
    }

    override fun setOnCameraMoveStartedListener(listener: OmhOnCameraMoveStartedListener) {
        // Todo Add listener when camera starts moving in the corresponding task.
    }

    override fun setOnCameraIdleListener(listener: OmhOnCameraIdleListener) {
        // Todo Add listener when camera finishes moving in the corresponding task.
    }
}
