package com.omh.android.maps.api.presentation.models

import android.os.Parcel
import android.os.Parcelable
import com.omh.android.maps.api.presentation.utils.Constants.DEFAULT_LATITUDE
import com.omh.android.maps.api.presentation.utils.Constants.DEFAULT_LONGITUDE
import com.omh.android.maps.api.presentation.utils.Constants.FULL_CIRCLE_DEGREES
import com.omh.android.maps.api.presentation.utils.Constants.HASH_MULTIPLIER
import com.omh.android.maps.api.presentation.utils.Constants.MAX_LATITUDE
import com.omh.android.maps.api.presentation.utils.Constants.MAX_LONGITUDE
import com.omh.android.maps.api.presentation.utils.Constants.MIN_LATITUDE
import com.omh.android.maps.api.presentation.utils.Constants.MIN_LONGITUDE
import kotlin.math.max
import kotlin.math.min

/**
 * Class representing a pair of latitude and longitude coordinates, stored as degrees.
 */
class OmhCoordinate() : Parcelable {

    /**
     * Latitude, in degrees. This value is in the range [-90, 90].
     */
    var latitude: Double = DEFAULT_LATITUDE

    /**
     * Longitude, in degrees. This value is in the range [-180, 180).
     */
    var longitude: Double = DEFAULT_LONGITUDE

    constructor(parcel: Parcel) : this() {
        latitude = parcel.readDouble()
        longitude = parcel.readDouble()
    }

    /**
     * Constructs a LatLng with the given latitude and longitude, measured in degrees.
     *
     * @param latitude -> The point's latitude.
     * This will be clamped to between -90 degrees and +90 degrees inclusive.
     * @param longitude -> The point's longitude.
     * This will be normalized to be within -180 degrees inclusive and +180 degrees exclusive.
     */
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
