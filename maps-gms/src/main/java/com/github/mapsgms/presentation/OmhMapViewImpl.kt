package com.github.mapsgms.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMapView
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhOnMapReadyCallBack
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer

class OmhMapViewImpl(context: Context) : OmhMapView {

    private var mapView: MapView? = null

    init {
        MapsInitializer.initialize(context, MapsInitializer.Renderer.LATEST, null)
        mapView = MapView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            onCreate(null)
        }
    }

    override fun getMapAsync(omhOnMapReadyCallBack: OmhOnMapReadyCallBack) {
        mapView?.getMapAsync { googleMap: GoogleMap ->
            val omhMap = OmhMapImpl(googleMap)
            omhOnMapReadyCallBack.onMapReady(omhMap)
        }
    }

    override fun getView(): View? {
        return mapView
    }

    internal class Builder : OmhMapView.Builder {

        override fun build(context: Context): OmhMapView {
            return OmhMapViewImpl(context)
        }
    }
}
