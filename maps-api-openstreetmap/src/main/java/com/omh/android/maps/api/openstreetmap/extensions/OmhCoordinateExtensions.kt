package com.omh.android.maps.api.openstreetmap.extensions

import com.omh.android.maps.api.presentation.models.OmhCoordinate
import org.osmdroid.util.GeoPoint

internal fun OmhCoordinate.toGeoPoint(): GeoPoint {
    return GeoPoint(latitude, longitude)
}
