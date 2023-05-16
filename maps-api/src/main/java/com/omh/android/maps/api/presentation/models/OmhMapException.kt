package com.omh.android.maps.api.presentation.models

sealed class OmhMapException(val statusCode: Int) : Exception() {

    class NullLocation(override val cause: Throwable? = null) :
        OmhMapException(OmhMapStatusCodes.LOCATION_ERROR)

    class ApiException(
        statusCode: Int,
        override val cause: Throwable? = null
    ) : OmhMapException(statusCode)

    class PermissionError(override val cause: Throwable? = null) :
        OmhMapException(OmhMapStatusCodes.INVALID_PERMISSION)

    override val message: String?
        get() = OmhMapStatusCodes.getStatusCodeString(statusCode)
}
