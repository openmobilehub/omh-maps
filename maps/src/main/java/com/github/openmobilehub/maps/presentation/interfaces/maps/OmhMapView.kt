package com.github.openmobilehub.maps.presentation.interfaces.maps

import android.content.Context
import android.view.View

interface OmhMapView {
    interface Builder {

        fun build(context: Context): OmhMapView
    }

    fun getView(): View?

    fun getMapAsync(omhOnMapReadyCallBack: OmhOnMapReadyCallBack)
}
