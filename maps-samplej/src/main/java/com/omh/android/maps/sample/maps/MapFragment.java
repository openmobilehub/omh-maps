package com.omh.android.maps.sample.maps;

import static com.omh.android.maps.sample.utils.Constants.ANIMATION_DURATION;
import static com.omh.android.maps.sample.utils.Constants.DEFAULT_ZOOM_LEVEL;
import static com.omh.android.maps.sample.utils.Constants.FINAL_TRANSLATION;
import static com.omh.android.maps.sample.utils.Constants.INITIAL_TRANSLATION;
import static com.omh.android.maps.sample.utils.Constants.LOCATION_KEY;
import static com.omh.android.maps.sample.utils.Constants.ONLY_DISPLAY_KEY;
import static com.omh.android.maps.sample.utils.Constants.OVERSHOOT_INTERPOLATOR;
import static com.omh.android.maps.sample.utils.Constants.PERMISSIONS;
import static com.omh.android.maps.sample.utils.Constants.PRIME_MERIDIAN;
import static com.omh.android.maps.sample.utils.Constants.ZOOM_LEVEL_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.omh.android.maps.api.presentation.fragments.OmhMapFragment;
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener;
import com.omh.android.maps.api.presentation.interfaces.location.OmhLocation;
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener;
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap;
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraIdleListener;
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnCameraMoveStartedListener;
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback;
import com.omh.android.maps.api.presentation.models.OmhCoordinate;
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions;
import com.omh.android.maps.api.utils.NetworkConnectivityChecker;
import com.omh.android.maps.sample.j.R;
import com.omh.android.maps.sample.j.databinding.FragmentMapBinding;
import com.omh.android.maps.sample.utils.BundleUtils;
import com.omh.android.maps.sample.utils.FactoryOmhLocation;
import com.omh.android.maps.sample.utils.PermissionsUtils;

public class MapFragment extends Fragment implements OmhOnMapReadyCallback {
    private OmhLocation omhLocation;
    private OmhCoordinate currentLocation = PRIME_MERIDIAN;
    private Boolean displayOnlyCoordinate = false;
    private FragmentMapBinding binding;
    private @Nullable NetworkConnectivityChecker networkConnectivityChecker = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            displayOnlyCoordinate = false;
        } else {
            displayOnlyCoordinate = savedInstanceState.getBoolean(ONLY_DISPLAY_KEY, false);
        }
        @Nullable OmhCoordinate coordinate = BundleUtils.getOmhCoordinate(savedInstanceState, LOCATION_KEY);
        if (coordinate != null) {
            currentLocation = coordinate;
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        omhLocation = FactoryOmhLocation.getOmhMapProvider(getContext());
        if (getArguments() != null) {
            @Nullable OmhCoordinate coordinate = MapFragmentArgs.fromBundle(getArguments()).getCoordinate();
            if (coordinate != null) {
                currentLocation = coordinate;
                displayOnlyCoordinate = true;
            }
        }

        networkConnectivityChecker = new NetworkConnectivityChecker(requireContext());
        NetworkConnectivityChecker.OmhOnLostConnection omhOnLostConnection = network -> Toast.makeText(requireContext(), R.string.lost_internet_connection, Toast.LENGTH_SHORT).show();
        networkConnectivityChecker.startListeningForConnectivityChanges(omhOnLostConnection);
        binding.fabShareLocation.setOnClickListener(v -> {
            MapFragmentDirections.ActionMapFragmentToInitialFragment action = MapFragmentDirections.actionMapFragmentToInitialFragment();
            action.setCoordinate(currentLocation);
            Navigation.findNavController(view).navigate(action);
        });

        View.OnClickListener onClickListener = v -> {
            boolean isVisible = binding.textViewLocation.getVisibility() == View.VISIBLE;

            if (isVisible) {
                binding.textViewLocation.setVisibility(View.GONE);
            } else {
                binding.textViewLocation.setVisibility(View.VISIBLE);
            }
        };
        binding.markerImageView.setOnClickListener(onClickListener);
        binding.textViewLocation.setOnClickListener(onClickListener);

        registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
            Fragment omhMapFragment = getChildFragmentManager().findFragmentById(R.id.fragment_map_container);
            if (omhMapFragment instanceof OmhMapFragment) {
                ((OmhMapFragment) omhMapFragment).getMapAsync(this);
            }
        }).launch(PERMISSIONS);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (networkConnectivityChecker != null) {
            networkConnectivityChecker.stopListeningForConnectivity();
        }
    }

    @Override
    public void onMapReady(@NonNull OmhMap omhMap) {

        if (networkConnectivityChecker != null && !networkConnectivityChecker.isNetworkAvailable()) {
            Toast.makeText(requireContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        }

        omhMap.setZoomGesturesEnabled(true);

        if (displayOnlyCoordinate) {
            displaySharedLocation(omhMap);
            return;
        }

        OmhOnCameraMoveStartedListener omhOnCameraMoveStartedListener = reason -> binding.markerImageView.animate()
                .translationY(INITIAL_TRANSLATION)
                .setInterpolator(OVERSHOOT_INTERPOLATOR)
                .setDuration(ANIMATION_DURATION)
                .start();

        omhMap.setOnCameraMoveStartedListener(omhOnCameraMoveStartedListener);

        OmhOnCameraIdleListener omhOnCameraIdleListener = () -> {
            binding.markerImageView.animate()
                    .translationY(FINAL_TRANSLATION)
                    .setInterpolator(OVERSHOOT_INTERPOLATOR)
                    .setDuration(ANIMATION_DURATION)
                    .start();

            currentLocation = omhMap.getCameraPositionCoordinate();
            binding.textViewLocation.setText(getString(
                    R.string.latitude_longitude_text,
                    String.valueOf(currentLocation.getLatitude()),
                    String.valueOf(currentLocation.getLongitude())
            ));
        };

        omhMap.setOnCameraIdleListener(omhOnCameraIdleListener);
        enableMyLocation(omhMap);
        moveToCurrentLocation(omhMap);
    }

    private void displaySharedLocation(OmhMap omhMap) {
        binding.fabShareLocation.setVisibility(View.GONE);
        binding.markerImageView.setVisibility(View.GONE);
        binding.markerShadowImageView.setVisibility(View.GONE);
        OmhMarkerOptions omhMarkerOptions = new OmhMarkerOptions();
        omhMarkerOptions.setPosition(currentLocation);
        omhMarkerOptions.setTitle(getString(
                R.string.latitude_longitude_text,
                String.valueOf(currentLocation.getLatitude()),
                String.valueOf(currentLocation.getLongitude())
        ));
        omhMap.addMarker(omhMarkerOptions);
        moveToCurrentLocation(omhMap, DEFAULT_ZOOM_LEVEL);
    }

    private void enableMyLocation(OmhMap omhMap) {
        if (PermissionsUtils.grantedRequiredPermissions(requireContext())) {
            // Safe use of 'noinspection MissingPermission' since it is checking permissions in the if condition
            // noinspection MissingPermission
            omhMap.setMyLocationEnabled(true);
            omhMap.setMyLocationButtonClickListener(() -> {
                Toast.makeText(requireContext(), R.string.center_message, Toast.LENGTH_SHORT).show();
                return false;
            });
        }
    }

    private void moveToCurrentLocation(OmhMap omhMap) {
        if (PermissionsUtils.grantedRequiredPermissions(requireContext())) {
            OmhSuccessListener onSuccessListener = omhCoordinate -> {
                currentLocation = omhCoordinate;
                moveToCurrentLocation(omhMap, DEFAULT_ZOOM_LEVEL);
            };
            OmhFailureListener onFailureListener = exception -> {
                currentLocation = PRIME_MERIDIAN;
                moveToCurrentLocation(omhMap, DEFAULT_ZOOM_LEVEL);
            };
            // Safe use of 'noinspection MissingPermission' since it is checking permissions in the if condition
            // noinspection MissingPermission
            omhLocation.getCurrentLocation(onSuccessListener, onFailureListener);
        } else {
            moveToCurrentLocation(omhMap, ZOOM_LEVEL_5);
        }
    }

    private void moveToCurrentLocation(OmhMap omhMap, Float zoomLevel) {
        omhMap.moveCamera(currentLocation, zoomLevel);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LOCATION_KEY, currentLocation);
        outState.putBoolean(ONLY_DISPLAY_KEY, displayOnlyCoordinate);
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }
}
