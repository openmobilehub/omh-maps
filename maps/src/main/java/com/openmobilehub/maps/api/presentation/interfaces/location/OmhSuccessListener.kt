package com.openmobilehub.maps.api.presentation.interfaces.location

import com.openmobilehub.maps.api.presentation.models.OmhCoordinate

fun interface OmhSuccessListener {
    fun onSuccess(omhCoordinate: OmhCoordinate)
}
