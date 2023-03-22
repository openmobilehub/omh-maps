package com.omh.android.maps.sample.maps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.omh.android.maps.api.googlemaps.presentation.OmhMapFactory
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.sample.databinding.ActivityMapBinding
import com.omh.android.maps.sample.start.InitialFragment
import com.omh.android.maps.sample.utils.Constants.ANIMATION_DURATION
import com.omh.android.maps.sample.utils.Constants.DEFAULT_ZOOM_LEVEL
import com.omh.android.maps.sample.utils.Constants.FINAL_TRANSLATION
import com.omh.android.maps.sample.utils.Constants.INITIAL_TRANSLATION
import com.omh.android.maps.sample.utils.Constants.OVERSHOOT_INTERPOLATOR
import com.omh.android.maps.sample.utils.Constants.PRIME_MERIDIAN
import com.omh.android.maps.api.presentation.interfaces.location.OmhOnMyLocationButtonClickListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMapView
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapActivity : AppCompatActivity(), OmhOnMapReadyCallback {

    @Inject
    lateinit var omhMapView: OmhMapView
    private val binding: ActivityMapBinding by lazy {
        ActivityMapBinding.inflate(layoutInflater)
    }
    private var currentLocation: OmhCoordinate = PRIME_MERIDIAN
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val view = omhMapView.getView()
        omhMapView.onCreate(savedInstanceState)

        omhMapView.getMapAsync(this)
        binding.frameLayoutContainer.addView(view)
        binding.fabShareLocation.setOnClickListener {
            finishAndReturnCoordinate()
        }
    }

    override fun onMapReady(omhMap: OmhMap) {
        omhMap.setZoomGesturesEnabled(true)
        omhMap.setMyLocationEnabled(true)

        val onSuccessListener = OmhSuccessListener { omhCoordinate ->
            currentLocation = omhCoordinate
            moveToCurrentLocation(omhMap)
        }

        val onFailureListener = OmhFailureListener {
            moveToCurrentLocation(omhMap)
        }

        val omhLocation = OmhMapFactory.getOmhLocation()
        omhLocation.getCurrentLocation(this@MapActivity, onSuccessListener, onFailureListener)

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

        val omhOnMyLocationButtonClickListener = OmhOnMyLocationButtonClickListener {
            omhLocation.getCurrentLocation(this@MapActivity, onSuccessListener, onFailureListener)
            moveToCurrentLocation(omhMap)
            true
        }
        omhMap.setMyLocationButtonClickListener(omhOnMyLocationButtonClickListener)
    }

    private fun moveToCurrentLocation(omhMap: OmhMap) {
        omhMap.moveCamera(currentLocation, DEFAULT_ZOOM_LEVEL)
    }

    private fun finishAndReturnCoordinate() {
        val returnIntent = Intent()
        returnIntent.putExtra(InitialFragment.LOCATION_RESULT, currentLocation)
        setResult(RESULT_OK, returnIntent)
        finish()
    }
}
