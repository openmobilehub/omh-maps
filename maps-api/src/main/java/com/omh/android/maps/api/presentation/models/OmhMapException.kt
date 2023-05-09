package com.omh.android.maps.api.presentation.models

import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.INVALID_ARGUMENT
import com.omh.android.maps.api.presentation.models.OmhMapStatusCodes.RUNTIME_ERROR

sealed class OmhMapException(val statusCode: Int) : Exception() {

    class NullLocation(override val cause: Throwable? = null) :
        OmhMapException(OmhMapStatusCodes.LOCATION_ERROR)

    class ApiException(
        statusCode: Int,
        override val cause: Throwable? = null
    ) : OmhMapException(statusCode)

    class InvalidArgument(
        statusCode: Int = INVALID_ARGUMENT,
        override val cause: Throwable? = null
    ) :
        OmhMapException(statusCode)

    class RunTimeError(statusCode: Int = RUNTIME_ERROR, override val cause: Throwable? = null) :
        OmhMapException(statusCode)

    class PermissionError(override val cause: Throwable? = null) :
        OmhMapException(OmhMapStatusCodes.INVALID_PERMISSION)

    override val message: String?
        get() = OmhMapStatusCodes.getStatusCodeString(statusCode)
}
