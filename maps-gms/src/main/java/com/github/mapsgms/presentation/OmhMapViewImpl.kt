package com.github.mapsgms.presentation

class OmhMapViewImpl : OmhMapView {

    private var mapView: MapView? = null

    override fun initialize(context: Context) {
        MapsInitializer.initialize(context, MapsInitializer.Renderer.LATEST, null)
        mapView = MapView(context)
    }

    override fun getMapAsync(omhOnMapReadyCallBack: OmhOnMapReadyCallBack) {
        mapView?.getMapAsync { googleMap ->
            val omhMap = OmhMapImpl(googleMap)
            omhOnMapReadyCallBack.onMapReady(omhMap)
        }
    }
}
