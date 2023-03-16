package com.openmobilehub.maps.sample.utils

import android.view.animation.OvershootInterpolator
import com.openmobilehub.maps.api.presentation.models.OmhCoordinate

object Constants {
    const val ANIMATION_DURATION = 250L
    val OVERSHOOT_INTERPOLATOR = OvershootInterpolator()
    val PRIME_MERIDIAN: OmhCoordinate = OmhCoordinate(51.4779, -0.0015)
    const val DEFAULT_ZOOM_LEVEL = 15f
    const val INITIAL_TRANSLATION = -75f
    const val FINAL_TRANSLATION = 0f
}