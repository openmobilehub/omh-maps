package com.omh.android.maps.sample.di

import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object OmhMapsModule {

    @Provides
    fun providesOmhLocation(provider: OmhMapProvider): OmhLocation {
        return provider.provideOmhLocation()
    }
}
