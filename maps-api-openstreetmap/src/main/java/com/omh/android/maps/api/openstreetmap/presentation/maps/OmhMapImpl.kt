package com.omh.android.maps.api.openstreetmap.presentation.maps

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.openstreetmap.extensions.toGeoPoint
import com.omh.android.maps.api.openstreetmap.extensions.toOmhCoordinate
import com.omh.android.maps.api.openstreetmap.utils.Constants.DEFAULT_ZOOM_LEVEL
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMyLocationButtonClickListener
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions
import org.osmdroid.api.IGeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class OmhMapImpl(private var mapView: MapView) : OmhMap {
    private var myLocationOverlay: MyLocationNewOverlay? = null
    private var myLocationIconOverlay: MyLocationIconOverlay? = null

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
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun setMyLocationEnabled(enable: Boolean) {
        if (!enable) { return }
        if (myLocationOverlay?.isMyLocationEnabled != true) {
            myLocationIconOverlay = MyLocationIconOverlay(mapView.context).apply {
                addOnClickListener { setMyLocationEnabled(true) }
            }
            myLocationOverlay = MyLocationNewOverlay(mapView).apply { enableMyLocation() }
            mapView.overlayManager.add(myLocationOverlay)
            mapView.overlayManager.add(myLocationIconOverlay)
        }
        myLocationOverlay?.myLocation?.let { geoPoint ->
            mapView.controller.animateTo(geoPoint)
            mapView.controller.setZoom(DEFAULT_ZOOM_LEVEL)
        }
    }

    override fun isMyLocationEnabled(): Boolean {
        return myLocationOverlay?.isMyLocationEnabled == true
    }

    override fun setMyLocationButtonClickListener(
        omhOnMyLocationButtonClickListener: OmhOnMyLocationButtonClickListener
    ) {
        myLocationIconOverlay?.addOnClickListener {
            omhOnMyLocationButtonClickListener.onMyLocationButtonClick()
        }
    }

    override fun setOnCameraMoveStartedListener(listener: OmhOnCameraMoveStartedListener) {
        // Todo Add listener when camera starts moving in the corresponding task.
    }

    override fun setOnCameraIdleListener(listener: OmhOnCameraIdleListener) {
        // Todo Add listener when camera finishes moving in the corresponding task.
    }
}
