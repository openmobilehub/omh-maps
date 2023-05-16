package com.omh.android.maps.api.presentation.models

object OmhMapStatusCodes {

    const val LOCATION_ERROR = 1
    const val DEVELOPER_ERROR = 2
    const val INTERNAL_ERROR = 3
    const val INVALID_PERMISSION = 5
    const val INVALID_PROVIDER = 7
    const val NULL_LISTENER = 8
    const val INVALID_LOOPER = 9
    const val NULL_LOCATION = 10

    fun getStatusCodeString(code: Int): String {
        return when (code) {
            LOCATION_ERROR -> "Location was not able to get"
            DEVELOPER_ERROR -> "DEVELOPER_ERROR"
            INTERNAL_ERROR -> "INTERNAL ERROR"
            INVALID_PERMISSION -> "No suitable permission is present"
            INVALID_PROVIDER -> "Provider is null or doesn't exist"
            NULL_LISTENER -> "Listener is null"
            INVALID_LOOPER -> "Calling thread has no Looper"
            NULL_LOCATION -> "Location is null"
            else -> "Unknown status code: $code"
        }
    }
}
