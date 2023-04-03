package com.omh.android.maps.sample.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.omh.android.maps.sample.utils.Constants.PERMISSIONS

object PermissionsUtils {
    fun grantedRequiredPermissions(context: Context): Boolean {
        return PERMISSIONS.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }
}
