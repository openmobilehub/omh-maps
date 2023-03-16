package com.openmobilehub.maps.api.presentation.interfaces.location

fun interface OmhFailureListener {
    fun onFailure(exception: Exception)
}
