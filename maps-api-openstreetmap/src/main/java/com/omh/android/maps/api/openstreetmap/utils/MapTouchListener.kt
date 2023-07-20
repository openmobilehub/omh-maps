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
