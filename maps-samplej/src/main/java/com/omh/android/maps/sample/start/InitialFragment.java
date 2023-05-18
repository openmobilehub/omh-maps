package com.omh.android.maps.sample.start;

import static com.omh.android.maps.sample.utils.Constants.AUTHORITY_URL;
import static com.omh.android.maps.sample.utils.Constants.LAT_PARAM;
import static com.omh.android.maps.sample.utils.Constants.LNG_PARAM;
import static com.omh.android.maps.sample.utils.Constants.PATH;
import static com.omh.android.maps.sample.utils.Constants.PERMISSIONS;
import static com.omh.android.maps.sample.utils.Constants.SCHEME_PROTOCOL;
import static com.omh.android.maps.sample.utils.Constants.TYPE_TEXT_PLAIN;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.omh.android.maps.api.presentation.models.OmhCoordinate;
import com.omh.android.maps.sample.j.R;
import com.omh.android.maps.sample.j.databinding.FragmentInitialBinding;

public class InitialFragment extends Fragment {
    private FragmentInitialBinding binding;
    private ActivityResultLauncher permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), permissions -> {
        for (Boolean element : permissions.values()) {
            if (!element) {
                return;
            }
        }
        NavHostFragment.findNavController(InitialFragment.this)
                .navigate(R.id.action_initialFragment_to_mapFragment);
    });

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentInitialBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            @Nullable OmhCoordinate coordinate = InitialFragmentArgs.fromBundle(getArguments()).getCoordinate();
            if (coordinate != null) {
                binding.textViewCoordinate.setText(coordinate.toString());
                binding.buttonShareLocation.setVisibility(View.VISIBLE);
                binding.buttonShareLocation.setOnClickListener(v-> intentSend(coordinate));
            }
        }
        binding.buttonOpenMap.setOnClickListener(v -> permissionLauncher.launch(PERMISSIONS));
    }

    private void intentSend(OmhCoordinate coordinate) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme(SCHEME_PROTOCOL)
                .authority(AUTHORITY_URL)
                .appendPath(PATH)
                .appendQueryParameter(LAT_PARAM, String.valueOf(coordinate.getLatitude()))
                .appendQueryParameter(LNG_PARAM, String.valueOf(coordinate.getLongitude()));
        intent.setType(TYPE_TEXT_PLAIN);
        intent.putExtra(Intent.EXTRA_TEXT, uriBuilder.build().toString());
        startActivity(Intent.createChooser(intent, String.valueOf(R.string.share_link)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static InitialFragment newInstance() {
        return new InitialFragment();
    }

}
