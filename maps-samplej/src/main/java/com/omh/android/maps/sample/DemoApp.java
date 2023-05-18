package com.omh.android.maps.sample;

import android.app.Application;

import com.omh.android.maps.api.factories.OmhMapProvider;
import com.omh.android.maps.api.factories.OmhMapSDK;
import com.omh.android.maps.sample.j.BuildConfig;

public class DemoApp extends Application {
    OmhMapProvider.Builder omhProviderBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        OmhMapProvider.Builder omhProviderBuilder = new OmhMapProvider.Builder();
        omhProviderBuilder
                .addGmsPath(BuildConfig.MAPS_GMS_PATH)
                .addNonGmsPath(BuildConfig.MAPS_NON_GMS_PATH);
        OmhMapSDK.initialize(omhProviderBuilder);
    }
}
