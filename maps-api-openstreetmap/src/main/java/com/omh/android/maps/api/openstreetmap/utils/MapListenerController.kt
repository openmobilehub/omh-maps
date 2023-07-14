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

package com.omh.android.maps.api.openstreetmap.utils

import android.os.Handler
import android.os.Looper
import com.omh.android.maps.api.openstreetmap.utils.Constants.DELAY_MS
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener.Companion.REASON_GESTURE
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent

internal class MapListenerController : MapListener {
    private var onCameraMoveStartedListener: OmhOnCameraMoveStartedListener? = null
    private var onIdleCameraListener: OmhOnCameraIdleListener? = null
    private var stopped = true
    private var touched = false
    private val handler = Handler(Looper.getMainLooper())
    private val idleRunnable = Runnable {
        if (isCameraIdle) {
            isMoving = false
            idleCamera()
        }
    }
    var isMoving = false
        private set
    private val isCameraIdle = (isMoving && stopped) || !touched

    override fun onScroll(event: ScrollEvent?): Boolean {
        return handleMove()
    }

    override fun onZoom(event: ZoomEvent?): Boolean {
        return handleMove()
    }

    fun handleMove(): Boolean {
        if (!isMoving) {
            isMoving = true
            stopped = false
            cameraMoveStarted()
        }
        handler.removeCallbacks(idleRunnable)
        handler.postDelayed(idleRunnable, DELAY_MS)

        return true
    }

    fun setOnStartListener(onStartListener: OmhOnCameraMoveStartedListener) {
        onCameraMoveStartedListener = onStartListener
    }

    fun setOnIdleListener(onIdleListener: OmhOnCameraIdleListener) {
        onIdleCameraListener = onIdleListener
    }

    private fun cameraMoveStarted() {
        onCameraMoveStartedListener?.onCameraMoveStarted(REASON_GESTURE)
    }

    private fun idleCamera() {
        onIdleCameraListener?.onCameraIdle()
    }

    fun setStopped(stopped: Boolean) {
        this.stopped = stopped
    }

    fun setTouch(touched: Boolean) {
        this.touched = touched
    }
}
