/*
 * Copyright 2023 Open Mobile Hub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
@SuppressWarnings("TooManyFunctions")
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

    /**
     * Sets a callback that's invoked when this map has finished rendering. The callback will only be invoked once.
     * If this method is called when the map is fully rendered, the callback will be invoked immediately.
     * This event will not fire if the map never loads due to connectivity issues, or if the map is continuously
     * changing and never completes loading due to the user constantly interacting with the map.
     *
     * @param callback The callback invoked when the map has finished rendering. To unset the callback, use null.
     */
    fun setOnMapLoadedCallback(callback: OmhMapLoadedCallback?)

    /**
     * Takes a snapshot of the map.
     *
     * You can use snapshots within your application when an interactive map would be difficult, or impossible,
     * to use. For example, images produced with the snapshot() method can be used to display a thumbnail of
     * the map in your app, or a snapshot in the notification center.
     *
     * @param omhSnapshotReadyCallback Callback method invoked when the snapshot is taken.
     */
    fun snapshot(omhSnapshotReadyCallback: OmhSnapshotReadyCallback)
}
