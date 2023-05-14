package com.omh.android.maps.api.openstreetmap.presentation.maps

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.view.MotionEvent
import android.view.ViewConfiguration
import com.omh.android.maps.api.openstreetmap.utils.Constants.MIN_DISTANCE
import com.omh.android.maps.api.openstreetmap.utils.Constants.ONE_POINTER
import com.omh.android.maps.api.openstreetmap.utils.Constants.PADDING_TEXT
import com.omh.android.maps.api.openstreetmap.utils.Constants.TEXT_COORDINATE_X
import com.omh.android.maps.api.openstreetmap.utils.Constants.TEXT_COPYRIGHT
import com.omh.android.maps.api.openstreetmap.utils.Constants.TEXT_SIZE
import com.omh.android.maps.api.openstreetmap.utils.Constants.TWO_POINTERS
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay
import kotlin.math.abs

internal class GestureOverlay : Overlay() {
    private var enableZoomGestures = true
    private var doubleTapped = false
    private var isScrolling = false
    private var lastPointerY = 0f
    private var startTouchTime = 0L
    private var wasDrawn = false
    private val paint = Paint().apply {
        color = Color.BLACK
        textSize = TEXT_SIZE
        typeface = Typeface.DEFAULT_BOLD
        isAntiAlias = true
    }

    override fun draw(canvas: Canvas?, mapView: MapView?, shadow: Boolean) {
        super.draw(canvas, mapView, shadow)
        if (!shadow && !wasDrawn) {
            val height = paint.fontMetrics.bottom - paint.fontMetrics.top - PADDING_TEXT
            val coordinateX = TEXT_COORDINATE_X
            val coordinateY = mapView?.height?.minus(height) ?: 0f
            canvas?.drawText(TEXT_COPYRIGHT, coordinateX, coordinateY, paint)
        }
    }

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
        val isTwoFingerTap = event.pointerCount == TWO_POINTERS && !isScrolling

        if (isTwoFingerTap) {
            mapView?.controller?.zoomOut()
        } else if (isSingleFingerDoubleTap(event, actionTime)) {
            mapView?.controller?.zoomIn()
        }
        lastPointerY = event.y
        doubleTapped = false
        isScrolling = false
    }

    private fun actionMove(event: MotionEvent, mapView: MapView?) {
        val isDoubleTapAndHoldFinger = doubleTapped && lastPointerY != event.y

        if (isDoubleTapAndHoldFinger && abs(lastPointerY - event.y) > MIN_DISTANCE) {
            zoomCamera(event.y, mapView)
        }
        lastPointerY = event.y
    }

    private fun isSingleFingerDoubleTap(event: MotionEvent, actionTime: Long): Boolean {
        val axisYNotMoved = event.y == lastPointerY
        val isOnePointer = event.pointerCount == ONE_POINTER
        val isInDoubleTapTime = actionTime - startTouchTime <= ViewConfiguration.getDoubleTapTimeout()
        return doubleTapped && axisYNotMoved && isOnePointer && isInDoubleTapTime
    }

    private fun zoomCamera(eventY: Float, mapView: MapView?) {
        if (lastPointerY - eventY > 0) {
            mapView?.controller?.zoomOut()
        } else {
            mapView?.controller?.zoomIn()
        }
        lastPointerY = eventY
    }

    fun setEnableZoomGestures(enableZoomGestures: Boolean) {
        this.enableZoomGestures = enableZoomGestures
    }
}
