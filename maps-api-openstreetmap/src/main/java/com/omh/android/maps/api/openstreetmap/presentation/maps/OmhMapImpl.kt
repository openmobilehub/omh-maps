package com.omh.android.maps.api.openstreetmap.presentation.maps

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.LocationManager.FUSED_PROVIDER
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.openstreetmap.extensions.toGeoPoint
import com.omh.android.maps.api.openstreetmap.extensions.toOmhCoordinate
import com.omh.android.maps.api.openstreetmap.utils.Constants.DEFAULT_ZOOM_LEVEL
import com.omh.android.maps.api.openstreetmap.utils.MapListenerController
import com.omh.android.maps.api.openstreetmap.utils.MapTouchListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMarker
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMyLocationButtonClickListener
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions
import org.osmdroid.api.IGeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

internal class OmhMapImpl(
    private val mapView: MapView,
    private val mapListenerController: MapListenerController
) : OmhMap {
    private var myLocationNewOverlay: MyLocationNewOverlay? = null
    private var myLocationIconOverlay: MyLocationIconOverlay? = null
    private val gestureOverlay = GestureOverlay()

    init {
        mapView.addMapListener(mapListenerController)
        mapView.setOnTouchListener(MapTouchListener(mapListenerController))
        mapView.overlayManager.add(gestureOverlay)
        setZoomGesturesEnabled(true)
    }

    override fun addMarker(options: OmhMarkerOptions): OmhMarker? {
        val marker: Marker = Marker(mapView).apply {
            position = options.position.toGeoPoint()
            title = options.title
        }
        mapView.run {
            overlayManager.add(marker)
            postInvalidate()
        }

        return OmhMarkerImpl(marker)
    }

    override fun getCameraPositionCoordinate(): OmhCoordinate {
        val centerPosition: IGeoPoint = mapView.mapCenter
        return centerPosition.toOmhCoordinate()
    }

    override fun moveCamera(coordinate: OmhCoordinate, zoomLevel: Float) {
        with(mapView.controller) {
            val geoPoint: IGeoPoint = coordinate.toGeoPoint()
            setZoom(zoomLevel.toDouble())
            setCenter(geoPoint)
        }
        mapView.postInvalidate()
    }

    override fun setZoomGesturesEnabled(enableZoomGestures: Boolean) {
        gestureOverlay.setEnableZoomGestures(enableZoomGestures)
        mapView.setMultiTouchControls(enableZoomGestures)
        if (!enableZoomGestures) {
            mapView.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
        }
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun setMyLocationEnabled(enable: Boolean) {
        if (enable) {
            enableMyLocation()
        } else {
            myLocationNewOverlay?.disableMyLocation()
            mapView.overlayManager.remove(myLocationIconOverlay)
            mapView.overlayManager.remove(myLocationNewOverlay)
        }
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    private fun enableMyLocation() {
        if (myLocationNewOverlay?.isMyLocationEnabled != true) {
            myLocationIconOverlay = MyLocationIconOverlay(mapView.context).apply {
                setCenterLocation { setMyLocationEnabled(true) }
            }
            val gpsMyLocationProvider = GpsMyLocationProvider(mapView.context).apply {
                addLocationSource(FUSED_PROVIDER)
            }
            myLocationNewOverlay = MyLocationNewOverlay(gpsMyLocationProvider, mapView).apply {
                enableMyLocation()
            }
            mapView.overlayManager.add(myLocationNewOverlay)
            mapView.overlayManager.add(myLocationIconOverlay)
        }
        myLocationNewOverlay?.myLocation?.let { geoPoint ->
            with(mapView.controller) {
                setZoom(DEFAULT_ZOOM_LEVEL)
                animateTo(geoPoint)
            }
            mapView.postInvalidate()
        }
    }

    override fun isMyLocationEnabled(): Boolean {
        return myLocationNewOverlay?.isMyLocationEnabled == true
    }

    override fun setMyLocationButtonClickListener(
        omhOnMyLocationButtonClickListener: OmhOnMyLocationButtonClickListener
    ) {
        myLocationIconOverlay?.setOnClickListener {
            omhOnMyLocationButtonClickListener.onMyLocationButtonClick()
        }
    }

    override fun setOnCameraMoveStartedListener(listener: OmhOnCameraMoveStartedListener) {
        mapListenerController.setOnStartListener(listener)
    }

    override fun setOnCameraIdleListener(listener: OmhOnCameraIdleListener) {
        mapListenerController.setOnIdleListener(listener)
    }
}
