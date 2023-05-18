package com.omh.android.maps.sample.utils;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.view.animation.OvershootInterpolator;

import com.omh.android.maps.api.presentation.models.OmhCoordinate;

import java.util.ArrayList;

public class Constants {

    // Coordinate constants
    public static final OmhCoordinate PRIME_MERIDIAN = new OmhCoordinate(51.4779, -0.0015);
    public static final Float DEFAULT_ZOOM_LEVEL = 15f;
    public static final Float ZOOM_LEVEL_5 = 5f;

    // Permissions
    public static final String[] PERMISSIONS = {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};

    // Animation constants
    public static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator();
    public static final Long ANIMATION_DURATION = 250L;
    public static final Float INITIAL_TRANSLATION = -75f;
    public static final Float FINAL_TRANSLATION = 0f;

    // Share intent
    public static final String TYPE_TEXT_PLAIN = "text/plain";
    public static final String LAT_PARAM = "lat";
    public static final String LNG_PARAM = "lng";
    public static final String AUTHORITY_URL = "com.omh.android.maps.samplej";
    public static final String SCHEME_PROTOCOL = "https";
    public static final String PATH = "maps";

    // Bundle keys
    public static final String LOCATION_KEY = "location";
    public static final String ONLY_DISPLAY_KEY = "only display";
}
