package com.omh.android.maps.starter_sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omh.android.maps.starter_sample.databinding.FragmentMapBinding
/**
 * TODO: Before you run your application import the required permissions.
 */

/**
 * TODO: Before you run your application extend `OmhOnMapReadyCallback`.
 */
class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * TODO: Before you run your application check the required permissions `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`.
         * TODO: Then get the `OmhMapFragment` and call the method `getMapAsync`.
         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * TODO: Before you run your application override `onMapReady` method of `OmhOnMapReadyCallback`.
     */

    /**
     * TODO: Before you run your application create the method `hasPermissions`
     */
}