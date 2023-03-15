package com.github.openmobilehub.maps.presentation.mapview

import android.content.Context
import android.widget.FrameLayout
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMapView
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhOnMapReadyCallBack
import com.github.openmobilehub.maps.presentation.interfaces.maps.SdkMapView

class OmhSdkMapView(context: Context) : SdkMapView, FrameLayout(context) {

    var mapView: OmhMapView? = null

    init {
        // mapView =
        // mapView?.getView()?.let {
        //    addView(it)
        // }
    }

    override fun getMapAsync(onMapReadyCallBack: OmhOnMapReadyCallBack) {
        mapView?.getMapAsync(onMapReadyCallBack)
    }
}
