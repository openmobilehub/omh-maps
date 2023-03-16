package com.github.openmobilehub.maps.presentation.interfaces.location

import com.github.openmobilehub.maps.presentation.models.OmhCoordinate

fun interface OmhSuccessListener {
    fun onSuccess(omhCoordinate: OmhCoordinate)
}
