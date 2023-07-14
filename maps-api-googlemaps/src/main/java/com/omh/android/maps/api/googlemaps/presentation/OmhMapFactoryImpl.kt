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

package com.omh.android.maps.api.googlemaps.presentation

import android.content.Context
import androidx.annotation.Keep
import com.omh.android.maps.api.factories.OmhMapFactory
import com.omh.android.maps.api.googlemaps.presentation.location.OmhLocationImpl
import com.omh.android.maps.api.googlemaps.presentation.maps.OmhMapViewImpl
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView

@Keep
internal object OmhMapFactoryImpl : OmhMapFactory {

    override fun getOmhMapView(context: Context): OmhMapView = OmhMapViewImpl.Builder().build(context)

    override fun getOmhLocation(context: Context): OmhLocation = OmhLocationImpl.Builder().build(context)
}
