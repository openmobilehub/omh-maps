package com.omh.android.maps.api.presentation.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

/**
 * Defines OmhMarkerOptions for a marker.
 *
 * Implements Parcelable interface to facilitate the usage.
 */
@Keep
class OmhMarkerOptions() : Parcelable {
    /**
     * The location for the marker.
     * Create a default position because the position cannot be null.
     */
    var position: OmhCoordinate = OmhCoordinate()

    /**
     * The title for the marker.
     */
    var title: String? = null

    /**
     * Constructs a OmhMarkerOptions with the given Parcel.
     *
     * @param parcel container for the OmhCoordinate.
     */
    constructor(parcel: Parcel) : this() {
        val omhCoordinate: OmhCoordinate? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcel.readParcelable(OmhCoordinate::class.java.classLoader, OmhCoordinate::class.java)
        } else {
            // Before Android 13, API level 33(Tiramisu) use:
            // fun <T : Parcelable?> readParcelable(loader: ClassLoader?): T
            @Suppress("DEPRECATION")
            parcel.readParcelable(OmhCoordinate::class.java.classLoader)
        }
        if (omhCoordinate != null) {
            position = omhCoordinate
        }
        title = parcel.readString()
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     * Value is either 0 or CONTENTS_FILE_DESCRIPTOR
     *
     * @return a bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     */
    override fun describeContents(): Int {
        return 0
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param parcel the Parcel in which the object should be written. This value cannot be null.
     * @param flags additional flags about how the object should be written.
     */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(position, flags)
        parcel.writeString(title)
    }

    /**
     * public CREATOR field that generates instances of your Parcelable class from a Parcel.
     */
    companion object CREATOR : Parcelable.Creator<OmhMarkerOptions> {
        /**
         * Create a new instance of the Parcelable class,
         * instantiating it from the given Parcel whose data had previously been written by Parcelable.writeToParcel().
         *
         * @param parcel the Parcel to read the object's data from.
         * @return a new instance of the Parcelable class.
         */
        override fun createFromParcel(parcel: Parcel): OmhMarkerOptions {
            return OmhMarkerOptions(parcel)
        }

        /**
         * Create a new array of the Parcelable class.
         *
         * @param size size of the array.
         * @return an array of the Parcelable class, with every entry initialized to null.
         */
        override fun newArray(size: Int): Array<OmhMarkerOptions?> {
            return arrayOfNulls(size)
        }
    }
}
