/*
 * Copyright 2023 Open Mobile Hub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omh.android.maps.api.presentation.interfaces.maps

/**
 * Abstraction to provide access to callback interface for when the camera motion starts.
 */
fun interface OmhOnCameraMoveStartedListener {

    /**
     * Called when the camera starts moving after it has been idle or when the reason for camera motion has changed.
     *
     * @param reason the reason for the camera change. Possible values:
     * REASON_GESTURE: User gestures on the map.
     * REASON_API_ANIMATION: Default animations resulting from user interaction.
     * REASON_DEVELOPER_ANIMATION: Developer animations.
     */
    fun onCameraMoveStarted(reason: Int)

    /**
     * int	[REASON_API_ANIMATION] Non-gesture animation initiated in response to user actions.
     * int	[REASON_DEVELOPER_ANIMATION] Developer initiated animation.
     * int	[REASON_GESTURE] Camera motion initiated in response to user gestures on the map.
     */
    companion object {
        const val REASON_GESTURE = 1
        const val REASON_API_ANIMATION = 2
        const val REASON_DEVELOPER_ANIMATION = 3
    }
}
