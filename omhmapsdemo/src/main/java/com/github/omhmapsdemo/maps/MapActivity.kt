package com.github.omhmapsdemo.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mapsgms.presentation.OmhMapFactory
import com.github.omhmapsdemo.databinding.ActivityMapBinding
import com.github.omhmapsdemo.utils.Constants.ANIMATION_DURATION
import com.github.omhmapsdemo.utils.Constants.DEFAULT_ZOOM_LEVEL
import com.github.omhmapsdemo.utils.Constants.FINAL_TRANSLATION
import com.github.omhmapsdemo.utils.Constants.INITIAL_TRANSLATION
import com.github.omhmapsdemo.utils.Constants.OVERSHOOT_INTERPOLATOR
import com.github.omhmapsdemo.utils.Constants.PRIME_MERIDIAN
import com.github.openmobilehub.maps.presentation.interfaces.location.OmhFailureListener
import com.github.openmobilehub.maps.presentation.interfaces.location.OmhSuccessListener
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMap
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMapView
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhOnCameraIdleListener
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhOnCameraMoveStartedListener
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhOnMapReadyCallBack
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapActivity : AppCompatActivity(), OmhOnMapReadyCallBack {

    @Inject
    lateinit var omhMapView: OmhMapView
    private lateinit var binding: ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        omhMapView.getMapAsync(this)
        val view = omhMapView.getView()
        binding.frameLayoutContainer.addView(view)
    }

    override fun onMapReady(omhMap: OmhMap) {
        omhMap.setZoomGesturesEnabled(true)
        omhMap.setMyLocationEnabled(true)

        val onSuccessListener = OmhSuccessListener { omhCoordinate -> omhMap.moveCamera(omhCoordinate, DEFAULT_ZOOM_LEVEL) }

        val onFailureListener = OmhFailureListener { omhMap.moveCamera(PRIME_MERIDIAN, DEFAULT_ZOOM_LEVEL) }

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

            omhMap.getCameraPositionCoordinate()
        }

        omhMap.setOnCameraIdleListener(omhOnCameraIdleListener)
    }
}
