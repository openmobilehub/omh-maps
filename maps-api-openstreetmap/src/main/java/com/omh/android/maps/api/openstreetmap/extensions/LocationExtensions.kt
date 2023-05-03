package com.omh.android.maps.api.openstreetmap.extensions

import android.location.Location
import com.omh.android.maps.api.presentation.models.OmhCoordinate

internal fun Location?.isMoreAccurateThan(otherLocation: Location?): Boolean {
    if (this == null) return false
    return otherLocation == null || this.accuracy < otherLocation.accuracy
}

internal fun Location.toOmhCoordinate(): OmhCoordinate {
    return OmhCoordinate(latitude, longitude)
}
