package com.omh.android.maps.api.presentation.models

import android.os.Parcel
import android.os.Parcelable
import com.omh.android.maps.api.presentation.utils.Constants.DEFAULT_LATITUDE
import com.omh.android.maps.api.presentation.utils.Constants.DEFAULT_LONGITUDE
import com.omh.android.maps.api.presentation.utils.Constants.HASH_MULTIPLIER

/**
 * Class representing a pair of latitude and longitude coordinates.
 * This class doesn't require to implement restrictions related with the latitude and longitude because
 * each time the interfaces are implemented they will parse to their own Coordinate model.
 *
 * Implements Parcelable interface to facilitate the usage.
 */
class OmhCoordinate() : Parcelable {

    /**
     * Latitude representation.
     */
    var latitude: Double = DEFAULT_LATITUDE

    /**
     * Longitude representation.
     */
    var longitude: Double = DEFAULT_LONGITUDE

    /**
     * Constructs a omhCoordinate with the given Parcel.
     *
     * @param parcel -> Container for the latitude and longitude.
     */
    constructor(parcel: Parcel) : this() {
        latitude = parcel.readDouble()
        longitude = parcel.readDouble()
    }

    /**
     * Constructs a OmhCoordinate with the given latitude and longitude.
     *
     * @param latitude -> The point's latitude.
     * @param longitude -> The point's longitude.
     */
    constructor(latitude: Double, longitude: Double) : this() {
        this.latitude = latitude
        this.longitude = longitude
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     * Value is either 0 or CONTENTS_FILE_DESCRIPTOR
     *
     * @return -> A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     */
    override fun describeContents(): Int {
        return 0
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param parcel -> The Parcel in which the object should be written. This value cannot be null.
     * @param flags -> Additional flags about how the object should be written.
     */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    /**
     * Returns a string representation of the object.
     *
     * @return -> Returns a default string representing the latitude and longitude.
     */
    override fun toString(): String {
        return "lat/lng: ($latitude,$longitude)"
    }

    /**
     * Compares if this OmhCoordinate is equal to another.
     *
     * @param other ->
     * @return -> True if and only if their latitudes are bitwise equal and their longitudes are bitwise equal.
     * Otherwise false.
     */
    override fun equals(other: Any?): Boolean {
        return if (other == null || other !is OmhCoordinate) {
            false
        } else {
            latitude == other.latitude && longitude == other.longitude
        }
    }

    /**
     * Returns hash code for the object.
     *
     * @return -> Returns an integer representing the current instance of the class.
     */
    override fun hashCode(): Int {
        return HASH_MULTIPLIER * latitude.hashCode() + longitude.hashCode()
    }

    /**
     * public CREATOR field that generates instances of your Parcelable class from a Parcel.
     */
    companion object CREATOR : Parcelable.Creator<OmhCoordinate> {
        /**
         * Create a new instance of the Parcelable class,
         * instantiating it from the given Parcel whose data had previously been written by Parcelable.writeToParcel().
         *
         * @param parcel -> The Parcel to read the object's data from.
         * @return -> Returns a new instance of the Parcelable class.
         */
        override fun createFromParcel(parcel: Parcel): OmhCoordinate {
            return OmhCoordinate(parcel)
        }

        /**
         * Create a new array of the Parcelable class.
         *
         * @param size -> Size of the array.
         * @return -> Returns an array of the Parcelable class, with every entry initialized to null.
         */
        override fun newArray(size: Int): Array<OmhCoordinate?> {
            return arrayOfNulls(size)
        }
    }
}
