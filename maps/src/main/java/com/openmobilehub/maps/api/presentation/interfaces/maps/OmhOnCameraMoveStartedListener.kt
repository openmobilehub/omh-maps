package com.openmobilehub.maps.api.presentation.interfaces.maps

/**
 * Abstraction to provide access to callback interface for when the camera motion starts.
 */
fun interface OmhOnCameraMoveStartedListener {

    /**
     * Called when the camera starts moving after it has been idle or when the reason for camera motion has changed.
     *
     * @param reason -> The reason for the camera change. Possible values:
     * REASON_GESTURE: User gestures on the map.
     * REASON_API_ANIMATION: Default animations resulting from user interaction.
     * REASON_DEVELOPER_ANIMATION: Developer animations.
     */
    fun onCameraMoveStarted(reason: Int)

    /**
     * int	REASON_API_ANIMATION -> Non-gesture animation initiated in response to user actions.
     * int	REASON_DEVELOPER_ANIMATION ->	Developer initiated animation.
     * int	REASON_GESTURE	-> Camera motion initiated in response to user gestures on the map.
     */
    companion object {
        const val REASON_GESTURE = 1
        const val REASON_API_ANIMATION = 2
        const val REASON_DEVELOPER_ANIMATION = 3
    }
}
