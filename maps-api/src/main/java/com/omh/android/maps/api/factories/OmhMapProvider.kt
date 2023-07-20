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

package com.omh.android.maps.api.factories

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import com.omh.android.maps.api.presentation.models.OmhMapException
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes
import kotlin.reflect.KClass

/**
 * Provides the correct implementation of the map and location for GMS or non GMS builds.
 */
class OmhMapProvider private constructor(
    private val gmsPath: String?,
    private val nonGmsPath: String?,
) {

    private val isSingleBuild = gmsPath != null && nonGmsPath != null

    /**
     * Provides [OmhMapView] interface to interact with the map from the OMH Maps library.
     *
     * @param context ideally your application context, but an activity context will also work.
     *
     * @return An [OmhMapView] to interact with the map from the OMH Maps library.
     * @throws [OmhMapException.ApiException] when reflection fails for any of the implementations
     * of the [OmhMapFactory]. If this happens, look if you have configured correctly the gradle plugin
     * or if your obfuscation method hasn't tampered with the library files.
     */
    fun provideOmhMapView(context: Context): OmhMapView {
        val omhMapFactory: OmhMapFactory = try {
            getOmhMapFactory(context)
        } catch (exception: ClassNotFoundException) {
            throw OmhMapException.ApiException(
                statusCode = OmhMapStatusCodes.DEVELOPER_ERROR,
                cause = exception
            )
        }
        return omhMapFactory.getOmhMapView(context)
    }

    /**
     * Provides [OmhLocation] interface to interact with the location from the OMH Maps library.
     *
     * @param context ideally your application context, but an activity context will also work.
     * @return [OmhLocation] to interact with the location from the OMH Maps library.
     * @throws [OmhMapException.ApiException] when reflection fails for any of the implementations
     * of the [OmhMapFactory]. If this happens, look if you have configured correctly the gradle plugin
     * or if your obfuscation method hasn't tampered with the library files.
     */
    fun provideOmhLocation(context: Context): OmhLocation {
        val omhMapFactory: OmhMapFactory = try {
            getOmhMapFactory(context)
        } catch (exception: ClassNotFoundException) {
            throw OmhMapException.ApiException(
                statusCode = OmhMapStatusCodes.DEVELOPER_ERROR,
                cause = exception
            )
        }
        return omhMapFactory.getOmhLocation(context)
    }

    /**
     * This uses reflection to obtain the correct implementation for GMS or non GMS devices
     * depending on what dependency you have.
     *
     * @return A [OmhMapFactory] instance that is created using reflection.
     * @throws [ClassNotFoundException] when the reflections fails.
     * Also can throw a [OmhMapException.ApiException] when no paths were provided.
     */
    private fun getOmhMapFactory(context: Context): OmhMapFactory = when {
        isSingleBuild -> reflectSingleBuild(context)
        gmsPath != null -> getFactoryImplementation(gmsPath)
        nonGmsPath != null -> getFactoryImplementation(nonGmsPath)
        else -> throw OmhMapException.ApiException(
            statusCode = OmhMapStatusCodes.DEVELOPER_ERROR,
            cause = IllegalStateException("NO PATHS PROVIDED")
        )
    }

    private fun reflectSingleBuild(context: Context): OmhMapFactory {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        return when (googleApiAvailability.isGooglePlayServicesAvailable(context)) {
            ConnectionResult.SUCCESS -> getFactoryImplementation(gmsPath!!)
            else -> getFactoryImplementation(nonGmsPath!!)
        }
    }

    private fun getFactoryImplementation(path: String): OmhMapFactory {
        val clazz: KClass<out Any> = Class.forName(path).kotlin
        return clazz.objectInstance as OmhMapFactory
    }

    /**
     * Initializator class to setup and create a [OmhMapProvider] instance.
     */
    class Initiator {

        private var gmsPath: String? = null
        private var nonGmsPath: String? = null

        /**
         * Adds a GMS path to the [OmhMapProvider].
         *
         * @param gmsPath The path for the GMS.
         */
        @JvmOverloads
        fun addGmsPath(gmsPath: String? = GMS_ADDRESS): Initiator {
            this.gmsPath = gmsPath
            return this
        }

        /**
         * Adds a Non GMS path to the [OmhMapProvider].
         *
         * @param ngmsPath The path for teh Non GMS.
         */
        @JvmOverloads
        fun addNonGmsPath(ngmsPath: String? = NON_GMS_ADDRESS): Initiator {
            this.nonGmsPath = ngmsPath
            return this
        }

        fun initialize() {
            singletonInstance = OmhMapProvider(gmsPath, nonGmsPath)
        }
    }

    /**
     * Object to hold and get the [OmhMapProvider].
     */
    companion object {
        private const val NON_GMS_ADDRESS =
            "com.omh.android.maps.api.openstreetmap.presentation.OmhMapFactoryImpl"
        private const val GMS_ADDRESS =
            "com.omh.android.maps.api.googlemaps.presentation.OmhMapFactoryImpl"

        internal var singletonInstance: OmhMapProvider? = null

        /**
         * Gets the [OmhMapProvider] instance.
         *
         * @return An existing [OmhMapProvider].
         * @throws [Error] when the [OmhMapProvider] is not initialized.
         */
        @JvmStatic
        fun getInstance(): OmhMapProvider {
            if (singletonInstance == null) error("SDK NOT INITIALIZED")
            return singletonInstance!!
        }
    }
}
