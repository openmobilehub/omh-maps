package com.github.omhmapsdemo.di

import android.content.Context
import com.github.mapsgms.presentation.OmhMapFactory
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMapView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun providesOmhMapView(@ApplicationContext appContext: Context): OmhMapView{
        return OmhMapFactory.getOmhMapView(appContext)
    }
}