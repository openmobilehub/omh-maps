package com.github.openmobilehub.maps.presentation.interfaces.maps

interface SdkMapView {
    fun getMapAsync(onMapReadyCallBack: OmhOnMapReadyCallBack)
}
