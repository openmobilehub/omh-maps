package com.omh.android.maps.sample.utils

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import com.omh.android.maps.api.presentation.models.OmhCoordinate

object BundleUtils {

    fun getOmhCoordinate(savedInstanceState: Bundle?, locationKey: String) =
        if (SDK_INT >= TIRAMISU) {
            savedInstanceState?.getParcelable(locationKey, OmhCoordinate::class.java)
        } else {
            // Before Android 13, API level 33(Tiramisu) use: fun <T : Parcelable?> getParcelable(name: String?): T
            @Suppress("DEPRECATION")
            savedInstanceState?.getParcelable(locationKey)
        }
}
