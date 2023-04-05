package com.omh.android.maps.api.openstreetmap.presentation.maps

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
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
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class OmhMapImpl(private var mapView: MapView) : OmhMap {

    private var isMyLocationEnabled = false
    private var myLocationOverlay: MyLocationNewOverlay? = null

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
        val geoPoint: IGeoPoint = coordinate.toGeoPoint()
        mapView.controller.setCenter(geoPoint)
        mapView.controller.setZoom(zoomLevel.toDouble())
    }

    override fun setZoomGesturesEnabled(enableZoomGestures: Boolean) {
        mapView.setMultiTouchControls(enableZoomGestures)
        // Todo Enable or disable missing gestures in the corresponding task.
    }

    override fun setMyLocationEnabled(enable: Boolean) {
        isMyLocationEnabled = enable
        // Todo Enable or disable MyLocation in the corresponding task.
        if (isMyLocationEnabled) {
            myLocationOverlay = MyLocationNewOverlay(mapView)
            myLocationOverlay?.enableMyLocation()
            myLocationOverlay?.enableFollowLocation()
            val currentDraw =
                ContextCompat.getDrawable(
                    mapView.context,
                    com.google.android.material.R.drawable.abc_ic_arrow_drop_right_black_24dp
                )
            var currentIcon: Bitmap? = null
            if (currentDraw != null) {
                currentIcon = (currentDraw as BitmapDrawable).bitmap
            }
            myLocationOverlay?.setPersonIcon(currentIcon)
            mapView.overlayManager.add(myLocationOverlay)
        }
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
