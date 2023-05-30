package com.omh.android.maps.sample

import android.app.Application
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.factories.OmhMapSDK

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val omhProviderBuilder = OmhMapProvider.Builder()
            .addGmsPath(BuildConfig.MAPS_GMS_PATH)
            .addNonGmsPath(BuildConfig.MAPS_NON_GMS_PATH)
        OmhMapSDK.initialize(omhProviderBuilder)
    }
}
