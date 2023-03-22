package com.omh.android.maps.sample.di

import android.content.Context
import com.omh.android.maps.api.googlemaps.presentation.OmhMapFactory
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object OmhMapsModule {

    @Provides
    fun providesOmhMapView(@ApplicationContext appContext: Context): OmhMapView {
        return OmhMapFactory.getOmhMapView(appContext)
    }
}