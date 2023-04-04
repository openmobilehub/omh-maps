package com.omh.android.maps.sample.maps

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.omh.android.maps.api.presentation.fragments.OmhMapFragment
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.sample.databinding.ActivityMapBinding
import com.omh.android.maps.sample.start.InitialFragment
import com.omh.android.maps.sample.utils.Constants.ANIMATION_DURATION
import com.omh.android.maps.sample.utils.Constants.DEFAULT_ZOOM_LEVEL
import com.omh.android.maps.sample.utils.Constants.FINAL_TRANSLATION
import com.omh.android.maps.sample.utils.Constants.INITIAL_TRANSLATION
import com.omh.android.maps.sample.utils.Constants.OVERSHOOT_INTERPOLATOR
import com.omh.android.maps.sample.utils.Constants.PERMISSIONS
import com.omh.android.maps.sample.utils.Constants.PRIME_MERIDIAN
import com.omh.android.maps.sample.utils.Constants.ZOOM_LEVEL_15
import com.omh.android.maps.sample.utils.PermissionsUtils.grantedRequiredPermissions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapActivity : AppCompatActivity(), OmhOnMapReadyCallback {

    @Inject
    lateinit var omhLocation: OmhLocation
    private var currentLocation: OmhCoordinate = PRIME_MERIDIAN
    private val binding: ActivityMapBinding by lazy {
        ActivityMapBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabShareLocation.setOnClickListener {
            finishAndReturnCoordinate()
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

        enableMyLocation(omhMap)
        moveToCurrentLocation(omhMap)
    }

    private fun enableMyLocation(omhMap: OmhMap) {
        if (grantedRequiredPermissions(this)) {
            // Safe use of 'noinspection MissingPermission' since it is checking permissions in the if condition
            // noinspection MissingPermission
            omhMap.setMyLocationEnabled(true)
            omhMap.setMyLocationButtonClickListener {
                moveToCurrentLocation(omhMap)
                true
            }
        }
    }

    private fun moveToCurrentLocation(omhMap: OmhMap) {
        if (grantedRequiredPermissions(this)) {
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
            omhLocation.getCurrentLocation(this, onSuccessListener, onFailureListener)
        } else {
            moveTo(omhMap, ZOOM_LEVEL_15)
        }
    }

    private fun moveTo(omhMap: OmhMap, zoomLevel: Float) {
        omhMap.moveCamera(currentLocation, zoomLevel)
    }

    private fun finishAndReturnCoordinate() {
        val returnIntent = Intent()
        returnIntent.putExtra(InitialFragment.LOCATION_RESULT, currentLocation)
        setResult(RESULT_OK, returnIntent)
        finish()
    }
}
