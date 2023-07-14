/*
 * Copyright 2023 Open Mobile Hub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omh.android.maps.api.presentation.fragments

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.INTERNET
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresPermission
import androidx.fragment.app.Fragment
import com.omh.android.maps.api.databinding.FragmentOmhMapBinding
import com.omh.android.maps.api.extensions.tag
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback
import com.omh.android.maps.api.utils.Constants.LOST_INTERNET_CONNECTION
import com.omh.android.maps.api.utils.Constants.NO_INTERNET_CONNECTION
import com.omh.android.maps.api.utils.NetworkConnectivityChecker

/**
 * A Map component in an app. This fragment is the simplest way to place a map in an application.
 * It's a wrapper around a view of a map to automatically handle the necessary life cycle needs.
 * Being a fragment, this component can be added to an activity's layout file simply with the XML below.
 */
@SuppressWarnings("TooManyFunctions")
class OmhMapFragment : Fragment() {

    private val logTag = OmhMapFragment::class.java.tag()
    private var _binding: FragmentOmhMapBinding? = null
    private val binding get() = _binding!!
    private var networkConnectivityChecker: NetworkConnectivityChecker? = null

    /**
     * [OmhMapView] instance to display in the view.
     */
    private var omhMapView: OmhMapView? = null

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null.
     * This will be called between onCreate and onViewCreated.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        omhMapView = context?.let { OmhMapProvider.getInstance().provideOmhMapView(it) }
        omhMapView?.onCreate(savedInstanceState)
        _binding = FragmentOmhMapBinding.inflate(inflater, container, false)
        val mapView = omhMapView?.getView()
        if (mapView != null) {
            binding.frameLayoutMapContainer.addView(mapView)
        }
        return binding.root
    }

    /**
     * Called immediately after onCreateView has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once they know their view hierarchy
     * has been completely created.
     * The fragment's view hierarchy is not however attached to its parent at this point.
     */
    @RequiresPermission(allOf = [ACCESS_NETWORK_STATE, INTERNET])
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkConnectivityChecker = context?.let { NetworkConnectivityChecker(it) }

        if (networkConnectivityChecker?.isNetworkAvailable() != true) {
            Log.w(logTag, NO_INTERNET_CONNECTION)
        }

        networkConnectivityChecker?.startListeningForConnectivityChanges {
            Log.w(logTag, LOST_INTERNET_CONNECTION)
        }
    }

    /**
     * Sets a callback object which will be triggered when the OmhMap instance is ready to be used.
     * Note that:
     * This method must be called from the main thread.
     * The callback will be executed in the main thread.
     *
     * @param omhOnMapReadyCallback the callback object that will be triggered when the map is ready to be used.
     */
    fun getMapAsync(omhOnMapReadyCallback: OmhOnMapReadyCallback) {
        omhMapView?.getMapAsync(omhOnMapReadyCallback)
    }

    /**
     * Recommended to set ViewBinding to null in onDestroyView.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        omhMapView?.onDestroy()
        networkConnectivityChecker?.stopListeningForConnectivity()
        super.onDestroy()
    }

    override fun onLowMemory() {
        omhMapView?.onLowMemory()
        super.onLowMemory()
    }

    override fun onPause() {
        omhMapView?.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        omhMapView?.onResume()
    }

    /**
     * Provides a Bundle to store the state of the Fragment before it gets destroyed.
     * It can later be retrieved when onCreate(Bundle) is called again.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        omhMapView?.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        omhMapView?.onStart()
    }

    override fun onStop() {
        omhMapView?.onStop()
        super.onStop()
    }

    companion object {
        /**
         * Creates a map fragment, using default options.
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment [OmhMapFragment].
         */
        @JvmStatic
        fun newInstance() = OmhMapFragment()
    }
}
