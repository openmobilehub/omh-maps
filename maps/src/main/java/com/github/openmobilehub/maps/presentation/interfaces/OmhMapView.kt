package com.github.openmobilehub.maps.presentation.interfaces

import android.content.Context

interface OmhMapView {
    fun initialize(context: Context)
    fun getMapAsync(omhOnMapReadyCallBack: OmhOnMapReadyCallBack)
}
