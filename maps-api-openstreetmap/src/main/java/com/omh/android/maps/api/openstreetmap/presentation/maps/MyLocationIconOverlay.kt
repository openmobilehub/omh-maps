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

package com.omh.android.maps.api.openstreetmap.presentation.maps

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.omh.android.maps.api.openstreetmap.utils.Constants.PADDING_MY_LOCATION_ICON
import org.osmdroid.library.R.drawable.ic_menu_mylocation
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

internal class MyLocationIconOverlay(context: Context) : Overlay() {
    private val icon: Drawable? = ContextCompat.getDrawable(context, ic_menu_mylocation)
    private var clickListener: (() -> Unit)? = null
    private var centerLocation: (() -> Unit)? = null
    private var wasDrawn = false

    override fun draw(canvas: Canvas?, mapView: MapView?, shadow: Boolean) {
        super.draw(canvas, mapView, shadow)
        if (wasDrawn || icon == null || mapView == null) return

        val coordinateX = mapView.width - icon.intrinsicWidth - PADDING_MY_LOCATION_ICON
        val coordinateY = PADDING_MY_LOCATION_ICON

        icon.setBounds(
            coordinateX,
            coordinateY,
            coordinateX + icon.intrinsicWidth,
            coordinateY + icon.intrinsicHeight
        )
        canvas?.let { icon.draw(it) }
    }

    override fun onTouchEvent(event: MotionEvent?, mapView: MapView?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP && mapView != null && icon != null) {
            val coordinateX = mapView.width - icon.intrinsicWidth - PADDING_MY_LOCATION_ICON
            val coordinateY = PADDING_MY_LOCATION_ICON
            val iconBounds = Rect(
                coordinateX,
                coordinateY,
                coordinateX + icon.intrinsicWidth,
                coordinateY + icon.intrinsicHeight
            )

            if (iconBounds.contains(event.x.toInt(), event.y.toInt())) {
                clickListener?.invoke()
                centerLocation?.invoke()
                return true
            }
        }
        return super.onTouchEvent(event, mapView)
    }

    fun setOnClickListener(onClick: () -> Unit) {
        clickListener = onClick
    }

    fun setCenterLocation(centerLocation: () -> Unit) {
        this.centerLocation = centerLocation
    }
}
