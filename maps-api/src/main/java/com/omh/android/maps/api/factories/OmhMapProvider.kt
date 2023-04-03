package com.omh.android.maps.api.factories

import android.content.Context
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import kotlin.reflect.KClass

/**
 * Object that provides the correct implementation of the client for GMS or non GMS builds.
 */
object OmhMapProvider {

    private const val NON_GMS_ADDRESS = "com.omh.android.maps.api.openstreetmaps.OmhMapFactoryImpl"
    private const val GMS_ADDRESS = "com.omh.android.maps.api.googlemaps.presentation.OmhMapFactoryImpl"

    /**
     * Provides a MapView interface to interact with the OMH Maps library. This uses reflection
     * to obtain the correct implementation for GMS or non GMS devices depending on what dependency
     * you have.
     *
     * @param context -> ideally your application context, but an activity context will also work.
     *
     * @return an [OmhMapView] to interact with the Auth module.
     */
    @SuppressWarnings("SwallowedException")
    fun provideOmhMapView(context: Context): OmhMapView {
        val omhMapFactory = try {
            val clazz: KClass<out Any> = Class.forName(GMS_ADDRESS).kotlin
            clazz.objectInstance as OmhMapFactory
        } catch (e: ClassNotFoundException) {
            val clazz: KClass<out Any> = Class.forName(NON_GMS_ADDRESS).kotlin
            clazz.objectInstance as OmhMapFactory
        }
        return omhMapFactory.getOmhMapView(context)
    }
}
