package com.omh.android.maps.api.openstreetmap.extensions

import com.omh.android.maps.api.presentation.models.OmhCoordinate
import org.osmdroid.api.IGeoPoint

internal fun IGeoPoint.toOmhCoordinate(): OmhCoordinate {
    return OmhCoordinate(latitude, longitude)
}