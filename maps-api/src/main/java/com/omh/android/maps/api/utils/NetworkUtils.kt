package com.omh.android.maps.api.utils

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.presentation.models.OmhMapException
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes

object NetworkUtils {

    @RequiresPermission(ACCESS_NETWORK_STATE)
    @Throws(OmhMapException.ApiException::class)
    @SuppressWarnings("TooGenericExceptionCaught")
    fun isNetworkAvailable(context: Context): Boolean {
        try {
            val connectivityManager: ConnectivityManager? =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = connectivityManager?.activeNetwork
                return if (network != null) {
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                    networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
                } else {
                    false
                }
            } else {
                @Suppress("DEPRECATION")
                // Before Android 6(API Level 23) it is safe to use this function
                return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
            }
        } catch (exception: RuntimeException) {
            throw OmhMapException.ApiException(OmhMapStatusCodes.INTERNAL_ERROR, exception)
        }
    }
}
