package com.omh.android.maps.api.presentation.interfaces.maps

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions

/**
 * Abstraction to provide access to the OmhMap. This is the main class of OMH Maps SDK
 * for Android and is the entry point for all methods related to the map.
 * You cannot instantiate a GoogleMap object directly, rather,
 * you must obtain one from the getMapAsync() method on a OmhMapView that you have added to your application.
 */
interface OmhMap {

    /**
     * Adds a marker to this map. The marker's icon is rendered on the map at the position.
     *
     * @param options a marker options object that defines how to render the marker.
     * @return [OmhMarker] that was added to the map.
     */
    fun addMarker(options: OmhMarkerOptions): OmhMarker?

    /**
     * Gets the camera's position.
     *
     * @return the position's coordinate.
     */
    fun getCameraPositionCoordinate(): OmhCoordinate

    /**
     * Moves the camera's position to a specific position.
     *
     * @param coordinate the position's coordinate that the camera will be moved
     * @param zoomLevel is the resolution of the current view. Zoom levels are between 0 and 18,
     * But some tiles might go beyond that.
     */
    fun moveCamera(coordinate: OmhCoordinate, zoomLevel: Float)

    /**
     * Enables or disables the zoom gestures in the map.
     *
     * @param enableZoomGestures true enables zoom gestures, false disables zoom gestures.
     */
    fun setZoomGesturesEnabled(enableZoomGestures: Boolean)

    /**
     * Enables or disables the my location layer.
     *
     * @param enable true enables the my location layer, false disables the my location layer.
     */
    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    fun setMyLocationEnabled(enable: Boolean)

    /**
     * Gets the status of the my-location layer.
     *
     * @return true if the my-location layer is enabled; false otherwise.
     */
    fun isMyLocationEnabled(): Boolean

    /**
     * Sets a callback that's invoked when the my location button is clicked.
     *
     * @param omhOnMyLocationButtonClickListener Callback interface for when the My Location button is clicked.
     */
    fun setMyLocationButtonClickListener(omhOnMyLocationButtonClickListener: OmhOnMyLocationButtonClickListener)

    /**
     * Sets the callback that's invoked when the camera starts moving or the reason for camera motion has changed.
     *
     * @param listener Callback interface for when the camera motion starts.
     */
    fun setOnCameraMoveStartedListener(listener: OmhOnCameraMoveStartedListener)

    /**
     * Sets the callback that is invoked when the camera movement has ended.
     *
     * @param listener Callback interface for when camera movement has ended.
     */
    fun setOnCameraIdleListener(listener: OmhOnCameraIdleListener)
}
