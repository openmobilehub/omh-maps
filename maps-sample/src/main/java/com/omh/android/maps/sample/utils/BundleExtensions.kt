package com.omh.android.maps.sample.utils

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import com.omh.android.maps.api.presentation.models.OmhCoordinate

fun Bundle.getOmhCoordinate(locationKey: String) =
    if (SDK_INT >= TIRAMISU) {
        getParcelable(locationKey, OmhCoordinate::class.java)
    } else {
        getParcelable(locationKey)
    }
