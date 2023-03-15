package com.github.openmobilehub.maps.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.openmobilehub.maps.databinding.FragmentOmhMapBinding
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMapView

class OmhMapFragment : Fragment() {
    private var omhMapView: OmhMapView? = null // How to inject the concrete implementation
    private var _binding: FragmentOmhMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOmhMapBinding.inflate(inflater, container, false)
        binding.frameLayoutContainer.addView(omhMapView?.getView())

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = OmhMapFragment()
    }
}
