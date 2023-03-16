package com.openmobilehub.maps.api.presentation.interfaces.maps

import android.content.Context
import android.view.View

interface OmhMapView {
    interface Builder {

        fun build(context: Context): OmhMapView
    }

    fun getView(): View?

    fun getMapAsync(omhOnMapReadyCallback: OmhOnMapReadyCallback)
}
