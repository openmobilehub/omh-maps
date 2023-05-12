package com.omh.android.maps.sample.di

import android.content.Context
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object OmhMapsModule {

    @Provides
    fun providesOmhLocation(
        @ApplicationContext context: Context
    ): OmhLocation {
        return OmhMapProvider.getInstance().provideOmhLocation(context)
    }
}
