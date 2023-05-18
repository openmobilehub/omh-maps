package com.omh.android.maps.sample.di

import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.sample.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    fun omhMapProvider(): OmhMapProvider.Builder {
        return OmhMapProvider.Builder()
            .addGmsPath(BuildConfig.MAPS_GMS_PATH)
            .addNonGmsPath(BuildConfig.MAPS_NON_GMS_PATH)
    }
}
