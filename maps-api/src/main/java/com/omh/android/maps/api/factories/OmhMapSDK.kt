package com.omh.android.maps.api.factories

/**
 * Object to initialize the Omh Map SDk.
 */
object OmhMapSDK {

    /**
     * Initializes the [OmhMapProvider].
     *
     * @param builder [OmhMapProvider.Builder] Initializes the [OmhMapProvider].
     */
    @JvmStatic
    fun initialize(builder: OmhMapProvider.Builder) {
        builder.build()
    }
}
