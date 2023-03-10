package com.github.openmobilehub.maps.presentation.mapview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.github.openmobilehub.maps.presentation.interfaces.OmhOnMapReadyCallBack
import com.github.openmobilehub.maps.presentation.interfaces.SdkMapView

class OmhSdkMapView(context: Context, attrs: AttributeSet) : SdkMapView, FrameLayout(context, attrs) {

    var mapView: OmhSdkMapView? = null

    init {
        // mapView = MapView(context, attrs)
        // addView(mapView)
    }

    override fun getMapAsync(onMapReadyCallBack: OmhOnMapReadyCallBack) {
        mapView?.getMapAsync(onMapReadyCallBack)
    }
}
