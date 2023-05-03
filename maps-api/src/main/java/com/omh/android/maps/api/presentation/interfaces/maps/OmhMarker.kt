package com.omh.android.maps.api.presentation.interfaces.maps

import com.omh.android.maps.api.presentation.models.OmhCoordinate

/**
 * Abstraction to provide access to [OmhMarker].
 * [OmhMarker] is an icon placed at a particular point on the map's surface.
 */
interface OmhMarker {

    /**
     * The [OmhCoordinate] value for the marker's position on the map.
     *
     * @return -> A [OmhCoordinate] object specifying the marker's current position.
     */
    fun getPosition(): OmhCoordinate

    /**
     * Sets the location of the marker.
     *
     * @param omhCoordinate -> Sets the location.
     */
    fun setPosition(omhCoordinate: OmhCoordinate)

    /**
     * A text string that's displayed in an info window when the user taps the marker.
     *
     * @return -> A string containing the marker's title.
     */
    fun getTitle(): String?

    /**
     * Sets the title of the marker.
     *
     * @param title -> Sets the title. If null, the title is cleared.
     */
    fun setTitle(title: String?)
}
