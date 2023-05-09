package com.omh.android.maps.api.openstreetmap.presentation.maps

import android.view.MotionEvent
import android.view.ViewConfiguration
import com.omh.android.maps.api.openstreetmap.utils.Constants.ONE_POINTER
import com.omh.android.maps.api.openstreetmap.utils.Constants.TWO_POINTERS
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

internal class GestureOverlay : Overlay() {
    private var enableZoomGestures = true
    private var doubleTapped = false
    private var isScrolling = false
    private var lastPointerY = 0f
    private var startTouchTime = 0L

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
        if (!enableZoomGestures) return super.onTouchEvent(event, mapView)
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastPointerY = event.y
                startTouchTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_MOVE -> {
                actionMove(event, mapView)
            }
            MotionEvent.ACTION_POINTER_UP,
            MotionEvent.ACTION_UP -> {
                actionUp(event, mapView)
            }
        }
        return super.onTouchEvent(event, mapView)
    }

    private fun actionUp(event: MotionEvent, mapView: MapView?) {
        val actionTime = System.currentTimeMillis()
        if (isTwoFingerTap(event.pointerCount)) {
            mapView?.controller?.zoomOut()
        } else if (isSingleFingerDoubleTap(event, actionTime)) {
            mapView?.controller?.zoomIn()
        }
        lastPointerY = event.y
        doubleTapped = false
        isScrolling = false
    }

    private fun actionMove(event: MotionEvent, mapView: MapView?) {
        if (isDoubleTapAndHoldFinger(event.y)) {
            zoomCamera(event.y, mapView)
        }
    }

    private fun isSingleFingerDoubleTap(event: MotionEvent, actionTime: Long): Boolean {
        val axisYNotMoved = event.y == lastPointerY
        val isOnePointer = event.pointerCount == ONE_POINTER
        val isInDoubleTapTime = actionTime - startTouchTime <= ViewConfiguration.getDoubleTapTimeout()
        return doubleTapped && axisYNotMoved && isOnePointer && isInDoubleTapTime
    }

    private fun isTwoFingerTap(eventPointerCount: Int) =
        eventPointerCount == TWO_POINTERS && !isScrolling

    private fun zoomCamera(eventY: Float, mapView: MapView?) {
        if (lastPointerY - eventY > 0) {
            mapView?.controller?.zoomOut()
        } else {
            mapView?.controller?.zoomIn()
        }
        lastPointerY = eventY
    }

    private fun isDoubleTapAndHoldFinger(eventY: Float) =
        doubleTapped && lastPointerY != eventY

    fun setEnableZoomGestures(enableZoomGestures: Boolean) {
        this.enableZoomGestures = enableZoomGestures
    }
}
