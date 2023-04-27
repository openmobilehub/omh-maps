package com.omh.android.maps.sample.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.omh.android.maps.api.presentation.fragments.OmhMapFragment
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.sample.databinding.FragmentMapBinding
import com.omh.android.maps.sample.utils.BundleUtils.getOmhCoordinate
import com.omh.android.maps.sample.utils.Constants.ANIMATION_DURATION
import com.omh.android.maps.sample.utils.Constants.DEFAULT_ZOOM_LEVEL
import com.omh.android.maps.sample.utils.Constants.FINAL_TRANSLATION
import com.omh.android.maps.sample.utils.Constants.INITIAL_TRANSLATION
import com.omh.android.maps.sample.utils.Constants.LOCATION_KEY
import com.omh.android.maps.sample.utils.Constants.ONLY_DISPLAY_KEY
import com.omh.android.maps.sample.utils.Constants.OVERSHOOT_INTERPOLATOR
import com.omh.android.maps.sample.utils.Constants.PERMISSIONS
import com.omh.android.maps.sample.utils.Constants.PRIME_MERIDIAN
import com.omh.android.maps.sample.utils.Constants.ZOOM_LEVEL_5
import com.omh.android.maps.sample.utils.PermissionsUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : Fragment(), OmhOnMapReadyCallback {
    @Inject
    lateinit var omhLocation: OmhLocation
    private var currentLocation: OmhCoordinate = PRIME_MERIDIAN
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private var displayOnlyCoordinate = false
    private val args: MapFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayOnlyCoordinate = savedInstanceState?.getBoolean(ONLY_DISPLAY_KEY, false) ?: false
        val coordinate = getOmhCoordinate(savedInstanceState, LOCATION_KEY)
        coordinate?.let {
            currentLocation = coordinate
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coordinate: OmhCoordinate? = args.coordinate
        coordinate?.let {
            currentLocation = coordinate
            displayOnlyCoordinate = true
        }

        binding.fabShareLocation.setOnClickListener {
            val action = MapFragmentDirections.actionMapFragmentToInitialFragment(currentLocation)
            findNavController().navigate(action)
        }

        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            val omhMapFragment: OmhMapFragment = binding.fragmentMapContainer.getFragment()
            omhMapFragment.getMapAsync(this)
        }.launch(PERMISSIONS)
    }

    override fun onMapReady(omhMap: OmhMap) {
        omhMap.setZoomGesturesEnabled(true)

        val omhOnCameraMoveStartedListener = OmhOnCameraMoveStartedListener {
            binding.markerImageView.animate()
                .translationY(INITIAL_TRANSLATION)
                .setInterpolator(OVERSHOOT_INTERPOLATOR)
                .setDuration(ANIMATION_DURATION)
                .start()
        }

        omhMap.setOnCameraMoveStartedListener(omhOnCameraMoveStartedListener)

        val omhOnCameraIdleListener = OmhOnCameraIdleListener {
            binding.markerImageView.animate()
                .translationY(FINAL_TRANSLATION)
                .setInterpolator(OVERSHOOT_INTERPOLATOR)
                .setDuration(ANIMATION_DURATION)
                .start()

            currentLocation = omhMap.getCameraPositionCoordinate()
            binding.textViewLocation.text = currentLocation.toString()
        }

        omhMap.setOnCameraIdleListener(omhOnCameraIdleListener)

        if (displayOnlyCoordinate) {
            moveTo(omhMap, DEFAULT_ZOOM_LEVEL)
        } else {
            enableMyLocation(omhMap)
            moveToCurrentLocation(omhMap)
        }
    }

    private fun enableMyLocation(omhMap: OmhMap) {
        if (PermissionsUtils.grantedRequiredPermissions(requireContext())) {
            // Safe use of 'noinspection MissingPermission' since it is checking permissions in the if condition
            // noinspection MissingPermission
            omhMap.setMyLocationEnabled(true)
        }
    }

    private fun moveToCurrentLocation(omhMap: OmhMap) {
        if (PermissionsUtils.grantedRequiredPermissions(requireContext())) {
            val onSuccessListener = OmhSuccessListener { omhCoordinate ->
                currentLocation = omhCoordinate
                moveTo(omhMap, DEFAULT_ZOOM_LEVEL)
            }
            val onFailureListener = OmhFailureListener {
                currentLocation = PRIME_MERIDIAN
                moveTo(omhMap, DEFAULT_ZOOM_LEVEL)
            }
            // Safe use of 'noinspection MissingPermission' since it is checking permissions in the if condition
            // noinspection MissingPermission
            omhLocation.getCurrentLocation(onSuccessListener, onFailureListener)
        } else {
            moveTo(omhMap, ZOOM_LEVEL_5)
        }
    }

    private fun moveTo(omhMap: OmhMap, zoomLevel: Float) {
        omhMap.moveCamera(currentLocation, zoomLevel)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(LOCATION_KEY, currentLocation)
        outState.putBoolean(ONLY_DISPLAY_KEY, displayOnlyCoordinate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}
