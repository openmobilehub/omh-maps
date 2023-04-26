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
    private var onCameraMoveStartedListenerList: MutableList<OmhOnCameraMoveStartedListener> = mutableListOf()
    private var onIdleCameraListenerList: MutableList<OmhOnCameraIdleListener> = mutableListOf()
    private var stopped = true
    private val handler = Handler(Looper.getMainLooper())
    private val idleRunnable = Runnable {
        if (stopped) {
            isMoving = false
            idleCamera()
        }
    }
    var isMoving = false
        private set

    override fun onScroll(event: ScrollEvent?): Boolean {
        return handleMove()
    }

    override fun onZoom(event: ZoomEvent?): Boolean {
        return handleMove()
    }

    private fun handleMove(): Boolean {
        if (!isMoving) {
            isMoving = true
            cameraMoveStarted()
        }
        handler.removeCallbacks(idleRunnable)
        handler.postDelayed(idleRunnable, DELAY_MS)

        return true
    }

    fun addOnStartListener(onStartListener: OmhOnCameraMoveStartedListener) {
        onCameraMoveStartedListenerList.add(onStartListener)
    }

    fun addOnIdleListener(onIdleListener: OmhOnCameraIdleListener) {
        onIdleCameraListenerList.add(onIdleListener)
    }

    fun cameraMoveStarted() {
        onCameraMoveStartedListenerList.forEach { listener ->
            listener.onCameraMoveStarted(REASON_GESTURE)
        }
    }

    fun idleCamera() {
        onIdleCameraListenerList.forEach { listener ->
            listener.onCameraIdle()
        }
    }

    fun setStopped(stopped: Boolean) {
        this.stopped = stopped
    }
}
