package com.omh.android.maps.sample

import android.app.Application
import com.omh.android.maps.api.factories.OmhMapProvider

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        OmhMapProvider.Initiator()
            .addGmsPath(BuildConfig.MAPS_GMS_PATH)
            .addNonGmsPath(BuildConfig.MAPS_NON_GMS_PATH)
            .initialize()
    }
}
