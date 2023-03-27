package com.omh.android.maps.api.googlemaps.utils

import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener

object LocationUtils {
    fun createCancellationToken() = object : CancellationToken() {
        override fun onCanceledRequested(onTokenCanceledListener: OnTokenCanceledListener): CancellationToken {
            return CancellationTokenSource().token
        }

        override fun isCancellationRequested(): Boolean {
            return false
        }
    }
}
