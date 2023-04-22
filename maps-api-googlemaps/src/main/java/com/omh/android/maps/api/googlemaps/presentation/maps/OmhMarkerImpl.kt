package com.omh.android.maps.api.googlemaps.presentation.maps

import com.google.android.gms.maps.model.Marker
import com.omh.android.maps.api.googlemaps.utils.ConverterUtils
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMarker
import com.omh.android.maps.api.presentation.models.OmhCoordinate

internal class OmhMarkerImpl(private val marker: Marker) : OmhMarker {

    override fun getPosition(): OmhCoordinate {
        return ConverterUtils.convertToOmhCoordinate(marker.position)
    }

    override fun setPosition(omhCoordinate: OmhCoordinate) {
        marker.position = ConverterUtils.convertToLatLng(omhCoordinate)
    }

    override fun getTitle(): String? {
        return marker.title
    }

    override fun setTitle(title: String?) {
        marker.title = title
    }
}
