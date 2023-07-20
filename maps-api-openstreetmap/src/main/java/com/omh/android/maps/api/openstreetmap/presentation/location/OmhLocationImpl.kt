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

package com.omh.android.maps.api.openstreetmap.presentation.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.openstreetmap.extensions.toOmhCoordinate
import com.omh.android.maps.api.openstreetmap.utils.LocationProviderClient
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener
import com.omh.android.maps.api.presentation.models.OmhMapException
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.NULL_LOCATION
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.getStatusCodeString

internal class OmhLocationImpl(context: Context) : OmhLocation {
    private val locationProviderClient = LocationProviderClient(context)

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun getCurrentLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        locationProviderClient.getCurrentLocation(
            onSuccess = { location: Location? ->
                handleOnSuccess(location, omhOnFailureListener, omhOnSuccessListener)
            },
            onFailure = { exception: Exception ->
                omhOnFailureListener.onFailure(exception)
            }
        )
    }

    @RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
    override fun getLastLocation(
        omhOnSuccessListener: OmhSuccessListener,
        omhOnFailureListener: OmhFailureListener
    ) {
        locationProviderClient.getLastLocation(
            onSuccess = { location: Location? ->
                handleOnSuccess(location, omhOnFailureListener, omhOnSuccessListener)
            },
            onFailure = { exception: Exception ->
                omhOnFailureListener.onFailure(exception)
            }
        )
    }

    private fun handleOnSuccess(
        location: Location?,
        omhOnFailureListener: OmhFailureListener,
        omhOnSuccessListener: OmhSuccessListener
    ) {
        if (location == null) {
            omhOnFailureListener.onFailure(
                exception = OmhMapException.NullLocation(
                    cause = IllegalStateException(getStatusCodeString(NULL_LOCATION))
                )
            )
        } else {
            omhOnSuccessListener.onSuccess(location.toOmhCoordinate())
        }
    }

    internal class Builder : OmhLocation.Builder {
        override fun build(context: Context): OmhLocation {
            return OmhLocationImpl(context)
        }
    }
}
