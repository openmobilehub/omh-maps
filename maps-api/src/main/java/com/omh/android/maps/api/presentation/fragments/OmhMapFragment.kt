package com.omh.android.maps.api.presentation.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.omh.android.maps.api.databinding.FragmentOmhMapBinding
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback

/**
 * A Map component in an app. This fragment is the simplest way to place a map in an application.
 * It's a wrapper around a view of a map to automatically handle the necessary life cycle needs.
 * Being a fragment, this component can be added to an activity's layout file simply with the XML below.
 */
@SuppressWarnings("TooManyFunctions")
class OmhMapFragment private constructor() : Fragment() {

    private var _binding: FragmentOmhMapBinding? = null
    private val binding get() = _binding!!

    /**
     * [OmhMapView] instance to display in the view.
     */
    private var omhMapView: OmhMapView? = null
    private val omhMapProvider: OmhMapProvider? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(OMH_PROVIDER_KEY, OmhMapProvider::class.java)
        } else {
            arguments?.getParcelable(OMH_PROVIDER_KEY) as? OmhMapProvider
        }
    }

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
        omhMapView = omhMapProvider?.provideOmhMapView(requireContext())
        omhMapView?.onCreate(savedInstanceState)
        _binding = FragmentOmhMapBinding.inflate(inflater, container, false)
        val mapView = omhMapView?.getView()
        if (mapView != null) {
            binding.frameLayoutMapContainer.addView(mapView)
        }
        return binding.root
    }

    /**
     * Sets a callback object which will be triggered when the OmhMap instance is ready to be used.
     * Note that:
     * This method must be called from the main thread.
     * The callback will be executed in the main thread.
     *
     * @param omhOnMapReadyCallback -> the callback object that will be triggered when the map is ready to be used.
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
        private const val OMH_PROVIDER_KEY = "omh provider key"

        /**
         * Creates a map fragment, using default options.
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment OmhMapFragment.
         */
        @JvmStatic
        fun newInstance(omhMapProvider: OmhMapProvider): OmhMapFragment {
            return OmhMapFragment().apply {
                arguments = bundleOf(OMH_PROVIDER_KEY to omhMapProvider)
            }
        }
    }
}
