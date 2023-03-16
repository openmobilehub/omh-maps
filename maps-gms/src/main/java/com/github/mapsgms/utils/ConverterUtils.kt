package com.github.mapsgms.utils

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.openmobilehub.maps.api.presentation.models.OmhCoordinate

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
