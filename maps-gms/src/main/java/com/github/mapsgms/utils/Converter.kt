package com.github.mapsgms.utils

import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.google.android.gms.maps.model.LatLng

object Converter {
    fun convertToCoordinate(latLng: LatLng?): OmhCoordinate {
        return if (latLng == null) {
            OmhCoordinate()
        } else {
            OmhCoordinate(latLng.latitude, latLng.longitude)
        }
    }

    fun convertToLatLng(coordinate: OmhCoordinate): LatLng {
        return LatLng(coordinate.latitude, coordinate.longitude)
    }
}
