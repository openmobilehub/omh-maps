package com.github.omhmapsdemo.di

import android.content.Context
import com.github.mapsgms.presentation.OmhMapFactory
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMapView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object OmhMapsModule {

    @Provides
    fun providesOmhMapView(@ApplicationContext appContext: Context): OmhMapView{
        return OmhMapFactory.getOmhMapView(appContext)
    }
}