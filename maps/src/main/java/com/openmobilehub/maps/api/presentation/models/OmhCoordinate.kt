package com.openmobilehub.maps.api.presentation.models

import android.os.Parcel
import android.os.Parcelable
import com.openmobilehub.maps.api.presentation.utils.Constants.DEFAULT_LATITUDE
import com.openmobilehub.maps.api.presentation.utils.Constants.DEFAULT_LONGITUDE
import com.openmobilehub.maps.api.presentation.utils.Constants.FULL_CIRCLE_DEGREES
import com.openmobilehub.maps.api.presentation.utils.Constants.HASH_MULTIPLIER
import com.openmobilehub.maps.api.presentation.utils.Constants.MAX_LATITUDE
import com.openmobilehub.maps.api.presentation.utils.Constants.MAX_LONGITUDE
import com.openmobilehub.maps.api.presentation.utils.Constants.MIN_LATITUDE
import com.openmobilehub.maps.api.presentation.utils.Constants.MIN_LONGITUDE
import kotlin.math.max
import kotlin.math.min

class OmhCoordinate() : Parcelable {

    var latitude: Double = DEFAULT_LATITUDE
    var longitude: Double = DEFAULT_LONGITUDE

    constructor(parcel: Parcel) : this() {
        latitude = parcel.readDouble()
        longitude = parcel.readDouble()
    }

    constructor(latitude: Double, longitude: Double) : this() {
        if (longitude >= MIN_LONGITUDE && longitude < MAX_LONGITUDE) {
            this.longitude = longitude
        } else {
            this.longitude = ((longitude + MIN_LONGITUDE) % FULL_CIRCLE_DEGREES + FULL_CIRCLE_DEGREES) %
                FULL_CIRCLE_DEGREES + MIN_LONGITUDE
        }
        this.latitude = max(MIN_LATITUDE, min(MAX_LATITUDE, latitude))
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun toString(): String {
        return "lat/lng: ($latitude,$longitude)"
    }

    override fun equals(other: Any?): Boolean {
        return if (other == null || other !is OmhCoordinate) {
            false
        } else {
            latitude == other.latitude && longitude == other.longitude
        }
    }

    override fun hashCode(): Int {
        return HASH_MULTIPLIER * latitude.hashCode() + longitude.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<OmhCoordinate> {
        override fun createFromParcel(parcel: Parcel): OmhCoordinate {
            return OmhCoordinate(parcel)
        }

        override fun newArray(size: Int): Array<OmhCoordinate?> {
            return arrayOfNulls(size)
        }
    }
}
