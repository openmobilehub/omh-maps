package com.omh.android.maps.api.factories

import android.content.Context
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import kotlin.reflect.KClass

/**
 * Object that provides the correct implementation of the map and location for GMS or non GMS builds.
 */
object OmhMapProvider {

    private const val NON_GMS_ADDRESS = "com.omh.android.maps.api.openstreetmap.presentation.OmhMapFactoryImpl"
    private const val GMS_ADDRESS = "com.omh.android.maps.api.googlemaps.presentation.OmhMapFactoryImpl"

    /**
     * Provides [OmhMapView] interface to interact with the map from the OMH Maps library.
     *
     * @param context -> ideally your application context, but an activity context will also work.
     *
     * @return an [OmhMapView] to interact with the map from the OMH Maps library.
     */

    fun provideOmhMapView(context: Context): OmhMapView {
        val omhMapFactory = getOmhMapFactory()
        return omhMapFactory.getOmhMapView(context)
    }

    /**
     * Provides [OmhLocation] interface to interact with the location from the OMH Maps library.
     *
     * @param context -> ideally your application context, but an activity context will also work.
     * @return -> [OmhLocation] to interact with the location from the OMH Maps library.
     */

    fun provideOmhLocation(context: Context): OmhLocation {
        val omhMapFactory = getOmhMapFactory()
        return omhMapFactory.getOmhLocation(context)
    }

    /**
     * This uses reflection to obtain the correct implementation for GMS or non GMS devices
     * depending on what dependency you have.
     *
     * @return -> a [OmhMapFactory] instance that is created using reflection.
     */
    @SuppressWarnings("SwallowedException")
    private fun getOmhMapFactory(): OmhMapFactory {
        val omhMapFactory = try {
            val clazz: KClass<out Any> = Class.forName(GMS_ADDRESS).kotlin
            clazz.objectInstance as OmhMapFactory
        } catch (e: ClassNotFoundException) {
            val clazz: KClass<out Any> = Class.forName(NON_GMS_ADDRESS).kotlin
            clazz.objectInstance as OmhMapFactory
        }
        return omhMapFactory
    }
}
