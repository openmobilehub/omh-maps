package com.github.openmobilehub.maps.presentation.interfaces.location

import com.github.openmobilehub.maps.presentation.models.OmhCoordinate

interface OmhSuccessListener {
    fun onSuccess(omhCoordinate: OmhCoordinate)
}
