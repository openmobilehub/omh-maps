package com.omh.android.maps.sample

import android.app.Application
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.factories.OmhMapSDK
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DemoApp : Application() {

    @Inject
    lateinit var omhProviderBuilder: OmhMapProvider.Builder

    override fun onCreate() {
        super.onCreate()
        OmhMapSDK.initialize(omhProviderBuilder)
    }
}
