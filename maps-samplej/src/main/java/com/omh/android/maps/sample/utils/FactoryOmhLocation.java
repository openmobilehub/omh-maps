package com.omh.android.maps.sample.utils;

import android.content.Context;

import com.omh.android.maps.api.factories.OmhMapProvider;
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation;

public class FactoryOmhLocation {
    public static OmhLocation getOmhMapProvider(Context context) {
        return OmhMapProvider.Companion.getInstance().provideOmhLocation(context);
    }
}
