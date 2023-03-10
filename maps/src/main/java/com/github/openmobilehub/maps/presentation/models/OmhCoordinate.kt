package com.github.openmobilehub.maps.presentation.models

class OmhCoordinate {

    var latitude: Double
    var longitude: Double

    constructor() {
        latitude = 0.0
        longitude = 0.0
    }

    constructor(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
    }
}
