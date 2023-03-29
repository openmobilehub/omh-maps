package com.omh.android.maps.sample.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.view.animation.OvershootInterpolator
import com.omh.android.maps.api.presentation.models.OmhCoordinate

object Constants {
    // Coordinate constants
    val PRIME_MERIDIAN: OmhCoordinate = OmhCoordinate(51.4779, -0.0015)
    const val DEFAULT_ZOOM_LEVEL = 15f
    const val ZOOM_LEVEL_15 = 5f

    // Animation constants
    val OVERSHOOT_INTERPOLATOR = OvershootInterpolator()
    const val ANIMATION_DURATION = 250L
    const val INITIAL_TRANSLATION = -75f
    const val FINAL_TRANSLATION = 0f

    // Permissions
    val PERMISSIONS: Array<String> = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
}