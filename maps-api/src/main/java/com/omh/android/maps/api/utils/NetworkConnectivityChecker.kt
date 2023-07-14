/*
 * Copyright 2023 Open Mobile Hub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omh.android.maps.api.utils

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.omh.android.maps.api.presentation.models.OmhMapException
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes

/**
 * Helper class to check the network connectivity.
 *
 * @param context to get the a system-level service class [ConnectivityManager].
 *
 * Note: System services obtained via this API may be closely associated with the Context
 * in which they are obtained from.
 * In general, do not share the service objects between various different contexts
 * (Activities, Applications, Services, Providers, etc.)
 */
class NetworkConnectivityChecker(context: Context) {
    /**
     * Inner interface to handle when the connection is lost.
     */
    fun interface OmhOnLostConnection {

        /**
         * Called when a network disconnects or otherwise no longer satisfies this request or callback.
         *
         * @param network the network is lost. This value cannot be null.
         */
        fun onLost(network: Network)
    }

    /**
     * Class that answers queries about the state of network connectivity.
     * It also notifies applications when network connectivity changes.
     */
    private val connectivityManager: ConnectivityManager? =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    /**
     * Base class for NetworkRequest callbacks. Used for notifications about network changes.
     * A NetworkCallback should be registered at most once at any time.
     * A NetworkCallback that has been unregistered can be registered again.
     */
    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    /**
     * Register callbacks to receive notifications when different network states change.
     *
     * @param onLostConnection callback called when a network disconnects or
     * otherwise no longer satisfies this request or callback.
     */
    @RequiresPermission(anyOf = [ACCESS_NETWORK_STATE])
    fun startListeningForConnectivityChanges(onLostConnection: OmhOnLostConnection) {
        stopListeningForConnectivity()
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                onLostConnection.onLost(network)
                super.onLost(network)
            }
        }
        val networkRequestBuilder = NetworkRequest.Builder().addCapability(NET_CAPABILITY_INTERNET)
        networkCallback?.let { callback ->
            connectivityManager?.registerNetworkCallback(networkRequestBuilder.build(), callback)
        }
    }

    /**
     * Unregisters the all registered callbacks if possible.
     */
    fun stopListeningForConnectivity() {
        networkCallback?.let { callback ->
            connectivityManager?.unregisterNetworkCallback(callback)
        }
    }

    /**
     * Checks if there is internet connection.
     *
     * @return true if there is internet connection otherwise returns false.
     */
    @RequiresPermission(anyOf = [ACCESS_NETWORK_STATE])
    @SuppressWarnings("TooGenericExceptionCaught")
    fun isNetworkAvailable(): Boolean {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return isConnectedToInternet()
            } else {
                @Suppress("DEPRECATION")
                // Before Android 6(API Level 23) it is safe to use this function
                return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
            }
        } catch (exception: RuntimeException) {
            throw OmhMapException.ApiException(OmhMapStatusCodes.INTERNAL_ERROR, exception)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @RequiresPermission(anyOf = [ACCESS_NETWORK_STATE])
    private fun isConnectedToInternet(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        return networkCapabilities != null &&
            networkCapabilities.hasCapability(NET_CAPABILITY_INTERNET) &&
            networkCapabilities.hasCapability(NET_CAPABILITY_VALIDATED)
    }
}
