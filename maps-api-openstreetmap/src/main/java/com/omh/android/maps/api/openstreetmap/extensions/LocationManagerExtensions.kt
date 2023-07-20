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

package com.omh.android.maps.api.openstreetmap.extensions

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.Location
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.presentation.models.OmhMapException
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.INVALID_PROVIDER

@RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
internal fun LocationManager.getLastKnownLocation(
    providers: List<String> = listOf(GPS_PROVIDER, NETWORK_PROVIDER)
): Location? {
    for (provider in providers) {
        val location = this.obtainLastKnownLocation(provider)
        if (location != null) return location
    }

    return null
}

@RequiresPermission(anyOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION])
@Throws(OmhMapException::class)
private fun LocationManager.obtainLastKnownLocation(provider: String): Location? {
    return try {
        getLastKnownLocation(provider)
    } catch (exception: SecurityException) {
        throw OmhMapException.PermissionError(exception)
    } catch (exception: IllegalArgumentException) {
        throw OmhMapException.ApiException(INVALID_PROVIDER, exception)
    }
}
