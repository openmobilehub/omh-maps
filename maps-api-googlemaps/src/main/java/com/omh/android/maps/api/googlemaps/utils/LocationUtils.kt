package com.omh.android.maps.api.googlemaps.utils

import com.google.android.gms.tasks.CancellationTokenSource

internal object LocationUtils {
    fun createCancellationToken() = CancellationTokenSource().token
}
