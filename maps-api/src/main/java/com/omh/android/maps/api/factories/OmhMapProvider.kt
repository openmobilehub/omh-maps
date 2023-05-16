package com.omh.android.maps.api.factories

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import kotlin.reflect.KClass

/**
 * Object that provides the correct implementation of the map and location for GMS or non GMS builds.
 */
class OmhMapProvider private constructor(
    private val gmsPath: String?,
    private val nonGmsPath: String?,
) {

    private val isSingleBuild = gmsPath != null && nonGmsPath != null

    /**
     * Provides [OmhMapView] interface to interact with the map from the OMH Maps library.
     *
     * @param context -> ideally your application context, but an activity context will also work.
     *
     * @return an [OmhMapView] to interact with the map from the OMH Maps library.
     */
    fun provideOmhMapView(context: Context): OmhMapView {
        val omhMapFactory = getOmhMapFactory(context)
        return omhMapFactory.getOmhMapView(context)
    }

    /**
     * Provides [OmhLocation] interface to interact with the location from the OMH Maps library.
     *
     * @param context -> ideally your application context, but an activity context will also work.
     * @return -> [OmhLocation] to interact with the location from the OMH Maps library.
     */
    fun provideOmhLocation(context: Context): OmhLocation {
        val omhMapFactory = getOmhMapFactory(context)
        return omhMapFactory.getOmhLocation(context)
    }

    /**
     * This uses reflection to obtain the correct implementation for GMS or non GMS devices
     * depending on what dependency you have.
     *
     * @return -> a [OmhMapFactory] instance that is created using reflection.
     */
    @Throws(Exception::class) // TODO map to OMH API exception with DEVELOPER status code
    private fun getOmhMapFactory(context: Context): OmhMapFactory = when {
        isSingleBuild -> reflectSingleBuild(context)
        gmsPath != null -> getFactoryImplementation(gmsPath)
        nonGmsPath != null -> getFactoryImplementation(nonGmsPath)
        else -> error("NO PATHS PROVIDED")
    }

    @Throws(ClassNotFoundException::class)
    private fun reflectSingleBuild(context: Context): OmhMapFactory {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        return when (googleApiAvailability.isGooglePlayServicesAvailable(context)) {
            ConnectionResult.SUCCESS -> getFactoryImplementation(gmsPath!!)
            else -> getFactoryImplementation(nonGmsPath!!)
        }
    }

    @Throws(ClassNotFoundException::class)
    private fun getFactoryImplementation(path: String): OmhMapFactory {
        val clazz: KClass<out Any> = Class.forName(path).kotlin
        return clazz.objectInstance as OmhMapFactory
    }

    class Builder {

        private var gmsPath: String? = null
        private var nonGmsPath: String? = null

        @JvmOverloads
        fun addGmsPath(gmsPath: String? = GMS_ADDRESS): Builder {
            this.gmsPath = gmsPath
            return this
        }

        @JvmOverloads
        fun addNonGmsPath(ngmsPath: String? = NON_GMS_ADDRESS): Builder {
            this.nonGmsPath = ngmsPath
            return this
        }

        internal fun build() {
            singletonInstance = OmhMapProvider(gmsPath, nonGmsPath)
        }
    }

    companion object {
        private const val NON_GMS_ADDRESS =
            "com.omh.android.maps.api.openstreetmap.presentation.OmhMapFactoryImpl"
        private const val GMS_ADDRESS =
            "com.omh.android.maps.api.googlemaps.presentation.OmhMapFactoryImpl"

        internal var singletonInstance: OmhMapProvider? = null

        fun getInstance(): OmhMapProvider {
            if (singletonInstance == null) error("SDK NOT INITIALIZED")
            return singletonInstance!!
        }
    }
}
