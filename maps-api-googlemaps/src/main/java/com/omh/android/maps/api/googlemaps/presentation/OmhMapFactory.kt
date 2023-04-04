package com.omh.android.maps.api.googlemaps.presentation

import android.content.Context
import com.omh.android.maps.api.googlemaps.presentation.location.OmhLocationImpl
import com.omh.android.maps.api.googlemaps.presentation.maps.OmhMapViewImpl
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView

object OmhMapFactory {

    fun getOmhMapView(context: Context): OmhMapView = OmhMapViewImpl.Builder().build(context)

    fun getOmhLocation(): OmhLocation {
        return OmhLocationImpl()
    }
}
