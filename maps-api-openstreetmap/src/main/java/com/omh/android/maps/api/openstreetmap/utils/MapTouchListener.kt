package com.omh.android.maps.api.openstreetmap.utils

import android.view.MotionEvent
import android.view.View

internal class MapTouchListener(private val mapListenerController: MapListenerController) :
    View.OnTouchListener {
    private var lastPointerX = 0f
    private var lastPointerY = 0f
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_POINTER_DOWN -> {
                mapListenerController.setTouch(true)
                lastPointerX = event.x
                lastPointerY = event.y
            }
            MotionEvent.ACTION_SCROLL,
            MotionEvent.ACTION_HOVER_MOVE -> {
                mapListenerController.handleMove()
            }
            MotionEvent.ACTION_MOVE -> {
                if (isMoving(event)) {
                    mapListenerController.handleMove()
                }
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_POINTER_UP -> {
                mapListenerController.setStopped(true)
                mapListenerController.setTouch(false)
                if (isMoving(event)) {
                    mapListenerController.handleMove()
                }
            }
        }

        return false
    }

    private fun isMoving(event: MotionEvent?) =
        event != null && eventMoved(event) && mapListenerController.isMoving

    private fun eventMoved(event: MotionEvent) = event.x != lastPointerX || event.y != lastPointerY
}
