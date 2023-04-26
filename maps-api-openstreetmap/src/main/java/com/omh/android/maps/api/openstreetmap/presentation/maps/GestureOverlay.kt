package com.omh.android.maps.api.openstreetmap.presentation.maps

import android.view.MotionEvent
import android.view.ViewConfiguration
import com.omh.android.maps.api.openstreetmap.utils.Constants.ONE_POINTER
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

const val TWO_POINTERS = 2

internal class GestureOverlay : Overlay() {
    private var enableZoomGestures = true
    private var doubleTapped = false
    private var isScrolling = false
    private var lastPointerY = 0f
    private var lastTime = 0L

    override fun onDoubleTap(event: MotionEvent?, mapView: MapView?): Boolean {
        doubleTapped = true
        return true
    }

    override fun onScroll(
        event1: MotionEvent?,
        event2: MotionEvent?,
        distanceX: Float,
        distanceY: Float,
        mapView: MapView?
    ): Boolean {
        isScrolling = true
        return super.onScroll(event1, event2, distanceX, distanceY, mapView)
    }

    override fun onTouchEvent(event: MotionEvent?, mapView: MapView?): Boolean {
        if (!enableZoomGestures) { return super.onTouchEvent(event, mapView) }
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                lastPointerY = event.y
                lastTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_MOVE -> {
                if (doubleTapped && lastPointerY != event.y) {
                    if (lastPointerY - event.y > 0) {
                        mapView?.controller?.zoomOut()
                    } else {
                        mapView?.controller?.zoomIn()
                    }
                    lastPointerY = event.y
                }
            }
            MotionEvent.ACTION_POINTER_UP,
            MotionEvent.ACTION_UP -> {
                val newTime = System.currentTimeMillis()
                if (event.pointerCount == TWO_POINTERS && !isScrolling) {
                    mapView?.controller?.zoomOut()
                }
                if (doubleTapped && event.y == lastPointerY && event.pointerCount == ONE_POINTER) {
                    if (newTime - lastTime <= ViewConfiguration.getDoubleTapTimeout()) {
                        mapView?.controller?.zoomIn()
                    }
                }
                lastPointerY = event.y
                doubleTapped = false
            }
        }
        isScrolling = false
        return super.onTouchEvent(event, mapView)
    }

    fun setEnableZoomGestures(enableZoomGestures: Boolean) {
        this.enableZoomGestures = enableZoomGestures
    }
}
