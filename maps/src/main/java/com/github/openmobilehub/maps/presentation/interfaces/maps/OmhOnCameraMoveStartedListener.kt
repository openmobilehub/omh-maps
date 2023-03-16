package com.github.openmobilehub.maps.presentation.interfaces.maps

fun interface OmhOnCameraMoveStartedListener {
    fun onCameraMoveStarted(var1: Int)

    companion object {
        const val REASON_GESTURE = 1
        const val REASON_API_ANIMATION = 2
        const val REASON_DEVELOPER_ANIMATION = 3
    }
}
