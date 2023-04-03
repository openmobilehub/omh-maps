package com.omh.android.maps.api.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.omh.android.maps.api.R
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback

/**
 * A simple [Fragment] subclass.
 * Use the [OmhMapFragment.newInstance] factory method to create an instance of this fragment.
 */
class OmhMapFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_omh_map, container, false)

        omhMapView = OmhMapProvider.provideOmhMapView(requireContext())
        omhMapView.onCreate(savedInstanceState)
        view.findViewById<FrameLayout>(R.id.frameLayout_mapContainer).addView(omhMapView.getView())

        return view
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

    companion object {
        /**
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment OmhMapFragment.
         */
        @JvmStatic
        fun newInstance() = OmhMapFragment()
    }
}
