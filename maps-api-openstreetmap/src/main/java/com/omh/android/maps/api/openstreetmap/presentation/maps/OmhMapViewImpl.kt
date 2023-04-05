package com.omh.android.maps.api.openstreetmap.presentation.maps

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceManager
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView

@Suppress("TooManyFunctions") // Suppress issue since interface has more than 12 functions.
class OmhMapViewImpl(context: Context) : OmhMapView {

    private var mapView: MapView? = null

    init {
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
        mapView = MapView(context)
    }

    override fun getView(): View? {
        return mapView
    }

    override fun getMapAsync(omhOnMapReadyCallback: OmhOnMapReadyCallback) {
        mapView?.let { secureMapView ->
            val omhMapView = OmhMapImpl(secureMapView)
            omhOnMapReadyCallback.onMapReady(omhMapView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mapView?.setTileSource(TileSourceFactory.MAPNIK)
    }

    override fun onDestroy() {
        // osmdroid doesn't implement this method
    }

    override fun onEnterAmbient(ambientDetails: Bundle) {
        // osmdroid doesn't implement this method
    }

    override fun onExitAmbient() {
        // osmdroid doesn't implement this method
    }

    override fun onLowMemory() {
        // osmdroid doesn't implement this method
    }

    override fun onPause() {
        mapView?.onPause()
    }

    override fun onResume() {
        mapView?.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // osmdroid doesn't implement this method
    }

    override fun onStart() {
        // osmdroid doesn't implement this method
    }

    override fun onStop() {
        // osmdroid doesn't implement this method
    }

    internal class Builder : OmhMapView.Builder {

        override fun build(context: Context): OmhMapView {
            return OmhMapViewImpl(context)
        }
    }
}
