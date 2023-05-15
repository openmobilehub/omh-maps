package com.omh.android.maps.api.googlemaps.presentation.maps

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback

@Suppress("TooManyFunctions") // Suppress issue since interface has more than 12 functions.
internal class OmhMapViewImpl(context: Context) : OmhMapView {

    private var mapView: MapView? = null

    init {
        MapsInitializer.initialize(context, MapsInitializer.Renderer.LEGACY, null)
        mapView = MapView(context)
    }

    override fun getMapAsync(omhOnMapReadyCallback: OmhOnMapReadyCallback) {
        mapView?.getMapAsync { googleMap: GoogleMap ->
            val omhMap = OmhMapImpl(googleMap)
            omhOnMapReadyCallback.onMapReady(omhMap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mapView?.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        mapView?.onLowMemory()
    }

    override fun onPause() {
        mapView?.onPause()
    }

    override fun onResume() {
        mapView?.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mapView?.onSaveInstanceState(outState)
    }

    override fun onStart() {
        mapView?.onStart()
    }

    override fun onStop() {
        mapView?.onStop()
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
