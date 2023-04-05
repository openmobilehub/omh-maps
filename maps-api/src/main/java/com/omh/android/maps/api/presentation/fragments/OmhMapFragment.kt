package com.omh.android.maps.api.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class OmhMapFragment : Fragment() {

    private var _binding: FragmentOmhMapBinding? = null
    private val binding get() = _binding!!

    /**
     * [OmhMapView] instance to display in the view.
     */
    private lateinit var omhMapView: OmhMapView

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null.
     * This will be called between onCreate and onViewCreated.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        omhMapView = OmhMapProvider.provideOmhMapView(requireContext())
        omhMapView.onCreate(savedInstanceState)
        _binding = FragmentOmhMapBinding.inflate(inflater, container, false)
        binding.frameLayoutMapContainer.addView(omhMapView.getView())

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
        omhMapView.getMapAsync(omhOnMapReadyCallback)
    }

    /**
     * You must call this method from the parent WearableActivity's corresponding method.
     */
    override fun onDestroy() {
        omhMapView.onDestroy()
        _binding = null
        super.onDestroy()
    }

    override fun onLowMemory() {
        omhMapView.onLowMemory()
        super.onLowMemory()
    }

    override fun onPause() {
        omhMapView.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        omhMapView.onResume()
    }

    /**
     * Provides a Bundle to store the state of the Fragment before it gets destroyed.
     * It can later be retrieved when onCreate(Bundle) is called again.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        omhMapView.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        omhMapView.onStart()
    }

    override fun onStop() {
        omhMapView.onStop()
        super.onStop()
    }

    companion object {
        /**
         * Creates a map fragment, using default options.
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment OmhMapFragment.
         */
        @JvmStatic
        fun newInstance() = OmhMapFragment()
    }
}
