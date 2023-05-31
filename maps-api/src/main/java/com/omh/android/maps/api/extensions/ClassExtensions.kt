package com.omh.android.maps.api.extensions

import com.omh.android.maps.api.utils.Constants.MAX_NAME_LENGTH
import com.omh.android.maps.api.utils.Constants.MIN_NAME_LENGTH

internal fun Class<*>.tag(): String {
    val simpleName = this.simpleName

    return if (simpleName.length > MAX_NAME_LENGTH) {
        simpleName.substring(MIN_NAME_LENGTH, MAX_NAME_LENGTH)
    } else {
        simpleName
    }
}
