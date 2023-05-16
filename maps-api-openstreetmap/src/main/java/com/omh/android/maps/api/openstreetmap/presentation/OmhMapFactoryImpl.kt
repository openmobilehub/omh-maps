package com.omh.android.maps.api.openstreetmap.presentation

import android.content.Context
import androidx.annotation.Keep
import com.omh.android.maps.api.factories.OmhMapFactory
import com.omh.android.maps.api.openstreetmap.presentation.location.OmhLocationImpl
import com.omh.android.maps.api.openstreetmap.presentation.maps.OmhMapViewImpl
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView

@Keep
internal object OmhMapFactoryImpl : OmhMapFactory {

    override fun getOmhMapView(context: Context): OmhMapView = OmhMapViewImpl.Builder().build(context)

    override fun getOmhLocation(context: Context): OmhLocation = OmhLocationImpl.Builder().build(context)
}
