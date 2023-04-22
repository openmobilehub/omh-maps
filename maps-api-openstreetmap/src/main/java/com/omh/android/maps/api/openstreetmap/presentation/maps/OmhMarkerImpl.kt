package com.omh.android.maps.api.openstreetmap.presentation.maps

import com.omh.android.maps.api.openstreetmap.extensions.toGeoPoint
import com.omh.android.maps.api.openstreetmap.extensions.toOmhCoordinate
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMarker
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import org.osmdroid.views.overlay.Marker

internal class OmhMarkerImpl(private val marker: Marker) : OmhMarker {

    override fun getPosition(): OmhCoordinate {
        return marker.position.toOmhCoordinate()
    }

    override fun setPosition(omhCoordinate: OmhCoordinate) {
        marker.position = omhCoordinate.toGeoPoint()
    }

    override fun getTitle(): String? {
        return marker.title
    }

    override fun setTitle(title: String?) {
        marker.title = title
    }
}
