package com.omh.android.maps.sample.utils;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.omh.android.maps.api.presentation.models.OmhCoordinate;

public class BundleUtils {

    public static @Nullable OmhCoordinate getOmhCoordinate(@Nullable Bundle savedInstanceState, String locationKey) {
        if (savedInstanceState == null) { return null; }
        return savedInstanceState.getParcelable(locationKey);
    }
}
