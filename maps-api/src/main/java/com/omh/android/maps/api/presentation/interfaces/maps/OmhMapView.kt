package com.omh.android.maps.api.presentation.interfaces.maps

import android.content.Context
import android.os.Bundle
import android.view.View

/**
 * Abstraction to provide access to a map view
 */
@Suppress("TooManyFunctions") // Suppress issue since interface has more than 12 functions.
interface OmhMapView {
    interface Builder {

        fun build(context: Context): OmhMapView
    }

    /**
     * Returns a View object of the map
     *
     * @return the map's view
     */
    fun getView(): View?

    /**
     * Returns a instance of the OmhMap through the callback, ready to be used.
     * Note that:
     * This method must be called from the main thread.
     * The callback will be executed in the main thread.
     *
     * @param omhOnMapReadyCallback -> the callback object that will be triggered when the map is ready to be used.
     */
    fun getMapAsync(omhOnMapReadyCallback: OmhOnMapReadyCallback)

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     *
     * @param savedInstanceState -> Stores a small amount of data needed to easily reload UI state
     * if the system stops and then recreates the UI
     */
    fun onCreate(savedInstanceState: Bundle?)

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    fun onDestroy()

    /**
     * You must call this method from the parent WearableActivity's corresponding method.
     */
    fun onEnterAmbient(ambientDetails: Bundle)

    /**
     * You must call this method from the parent WearableActivity's corresponding method.
     */
    fun onExitAmbient()

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    fun onLowMemory()

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    fun onPause()

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    fun onResume()

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     * Provides a Bundle to store the state of the View before it gets destroyed.
     * It can later be retrieved when onCreate(Bundle) is called again.
     */
    fun onSaveInstanceState(outState: Bundle)

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    fun onStart()

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    fun onStop()
}
