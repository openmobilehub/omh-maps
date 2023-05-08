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
                actionDown(event)
            }
            MotionEvent.ACTION_SCROLL,
            MotionEvent.ACTION_HOVER_MOVE -> {
                mapListenerController.handleMove()
            }
            MotionEvent.ACTION_MOVE -> {
                actionMove(event)
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_POINTER_UP -> {
                actionUp(event)
            }
        }

        return false
    }

    private fun actionUp(event: MotionEvent?) {
        mapListenerController.setStopped(true)
        mapListenerController.setTouch(false)
        actionMove(event)
    }

    private fun actionMove(event: MotionEvent?) {
        if (isMoving(event)) {
            mapListenerController.handleMove()
        }
    }

    private fun actionDown(event: MotionEvent) {
        mapListenerController.setTouch(true)
        lastPointerX = event.x
        lastPointerY = event.y
    }

    private fun isMoving(event: MotionEvent?) =
        event != null && eventMoved(event) && mapListenerController.isMoving

    private fun eventMoved(event: MotionEvent) = event.x != lastPointerX || event.y != lastPointerY
}
