package com.omh.android.maps.api.factories

import android.content.Context
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView

/**
 * A Factory to provide any of the interfaces of the OMH Maps Api module.
 * This isn't designed to be used directly from the client side, instead use the [OmhMapProvider]
 */
interface OmhMapFactory {

    /**
     * Provides the [OmhMapView] that is the main entry point with the OMH Maps module.
     *
     * @param context -> ideally your application context, but an activity context will also work.
     * @return An [OmhMapView] instance object.
     */
    fun getOmhMapView(context: Context): OmhMapView
}
