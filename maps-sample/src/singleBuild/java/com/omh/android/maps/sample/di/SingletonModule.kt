package com.omh.android.maps.sample.di

import android.content.Context
import com.omh.android.maps.api.factories.OmhMapProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    fun omhMapProvider(@ApplicationContext context: Context): OmhMapProvider {
        return OmhMapProvider.Builder()
            .addGmsPath()
            .addNonGmsPath()
            .build(context)
    }
}
