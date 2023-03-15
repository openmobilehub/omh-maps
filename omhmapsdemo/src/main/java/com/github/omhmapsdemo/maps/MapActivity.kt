package com.github.omhmapsdemo.maps

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.github.mapsgms.presentation.OmhMapFactory
import com.github.omhmapsdemo.databinding.ActivityMapBinding
import com.github.openmobilehub.maps.presentation.interfaces.location.OmhFailureListener
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhMap
import com.github.openmobilehub.maps.presentation.interfaces.maps.OmhOnMapReadyCallBack
import com.github.openmobilehub.maps.presentation.interfaces.location.OmhSuccessListener
import com.github.openmobilehub.maps.presentation.models.OmhCoordinate
import com.github.openmobilehub.maps.presentation.models.OmhOnCameraIdleListener
import com.github.openmobilehub.maps.presentation.models.OmhOnCameraMoveStartedListener

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val omhMapView = OmhMapFactory.getOmhMapView(this)

        val omhOnMapReadyCallBack = object: OmhOnMapReadyCallBack {
            override fun onMapReady(omhMap: OmhMap) {
                omhMap.setZoomGesturesEnabled(true)
                omhMap.setMyLocationEnabled(true)

                val onSuccessListener = object : OmhSuccessListener {
                    override fun onSuccess(omhCoordinate: OmhCoordinate) {
                        omhMap.moveCamera(omhCoordinate, 15f)
                    }
                }

                val onFailureListener = object : OmhFailureListener {
                    override fun onFailure(exception: Exception) {
                        omhMap.moveCamera(Companion.PRIME_MERIDIAN, 15f)
                    }
                }

                val omhLocation = OmhMapFactory.getOmhLocation()
                omhLocation.getCurrentLocation(this@MapActivity, onSuccessListener, onFailureListener)

                val omhOnCameraMoveStartedListener = object : OmhOnCameraMoveStartedListener {
                    override fun onCameraMoveStarted(reason: Int) {
                        binding.markerImageView.animate()
                            .translationY(-75f)
                            .setInterpolator(Companion.OVERSHOOT_INTERPOLATOR)
                            .setDuration(Companion.ANIMATION_DURATION.toLong())
                            .start()
                    }
                }

                omhMap.setOnCameraMoveStartedListener(omhOnCameraMoveStartedListener)

                val omhOnCameraIdleListener = object : OmhOnCameraIdleListener {
                    override fun onCameraIdle() {
                        binding.markerImageView.animate()
                            .translationY(0f)
                            .setInterpolator(Companion.OVERSHOOT_INTERPOLATOR)
                            .setDuration(Companion.ANIMATION_DURATION.toLong())
                            .start()

                        omhMap.getCameraPositionCoordinate()
                    }
                }

                omhMap.setOnCameraIdleListener(omhOnCameraIdleListener)
            }
        }

        omhMapView.getMapAsync(omhOnMapReadyCallBack)
        val view = omhMapView.getView()
        binding.frameLayoutContainer.addView(view)
    }

    companion object {
        private const val ANIMATION_DURATION = 250
        private val OVERSHOOT_INTERPOLATOR = OvershootInterpolator()
        private val PRIME_MERIDIAN: OmhCoordinate = OmhCoordinate(51.4779, -0.0015)
    }
}
