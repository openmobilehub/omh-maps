package com.omh.android.maps.api.googlemaps.extensions

import com.google.android.gms.maps.model.MarkerOptions
import com.omh.android.maps.api.googlemaps.utils.ConverterUtils
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions

internal fun OmhMarkerOptions.toMarkerOptions(): MarkerOptions {
    return MarkerOptions()
        .position(ConverterUtils.convertToLatLng(position))
        .title(title)
}
