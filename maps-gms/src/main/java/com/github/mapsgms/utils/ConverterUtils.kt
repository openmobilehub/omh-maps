package com.github.mapsgms.utils

import android.location.Location
import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.google.android.gms.maps.model.LatLng

object ConverterUtils {
    fun convertToOmhCoordinate(latLng: LatLng?): OmhCoordinate {
        return if (latLng == null) {
            OmhCoordinate()
        } else {
            OmhCoordinate(latLng.latitude, latLng.longitude)
        }
    }

    fun convertToLatLng(coordinate: OmhCoordinate): LatLng {
        return LatLng(coordinate.latitude, coordinate.longitude)
    }

    fun convertToOmhCoordinate(location: Location): OmhCoordinate {
        return OmhCoordinate(location.latitude, location.longitude)
    }
}
