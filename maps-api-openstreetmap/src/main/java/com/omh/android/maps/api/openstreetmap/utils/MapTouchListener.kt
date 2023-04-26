package com.omh.android.maps.api.openstreetmap.utils

import android.view.MotionEvent
import android.view.View
import com.omh.android.maps.api.openstreetmap.utils.Constants.ONE_POINTER

internal class MapTouchListener(private val mapListenerController: MapListenerController) : View.OnTouchListener {
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (event.pointerCount == ONE_POINTER) {
                    mapListenerController.cameraMoveStarted()
                }
            }
            MotionEvent.ACTION_UP -> {
                if (!mapListenerController.isMoving) {
                    mapListenerController.idleCamera()
                }
                mapListenerController.setStopped(true)
            }
        }

        return false
    }
}
