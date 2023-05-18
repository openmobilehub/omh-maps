package com.omh.android.maps.sample.utils;

import static com.omh.android.maps.sample.utils.Constants.PERMISSIONS;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class PermissionsUtils {
        public static Boolean grantedRequiredPermissions(Context context) {
            for (String element : PERMISSIONS) {
                if (!(ContextCompat.checkSelfPermission(context, element) == PackageManager.PERMISSION_GRANTED))
                    return false;
            }
            return true;
    }
}
