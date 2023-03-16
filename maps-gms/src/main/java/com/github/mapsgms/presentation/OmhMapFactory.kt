package com.github.mapsgms.presentation

import android.content.Context
import com.openmobilehub.maps.api.presentation.interfaces.location.OmhLocation
import com.openmobilehub.maps.api.presentation.interfaces.maps.OmhMapView

object OmhMapFactory {

    fun getOmhMapView(context: Context): OmhMapView {
        val builder = OmhMapViewImpl.Builder()
        return builder.build(context)
    }

    fun getOmhLocation(): OmhLocation {
        return OmhLocationImpl()
    }
}
