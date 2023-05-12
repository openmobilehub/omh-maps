package com.omh.android.maps.sample.di

import com.omh.android.maps.api.factories.OmhMapProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    fun omhMapProvider(): OmhMapProvider {
        return OmhMapProvider.Builder()
            .addGmsPath()
            .build()
    }
}