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

        if (wasDrawn) return
        if (!shadow && icon != null && mapView != null) {
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
