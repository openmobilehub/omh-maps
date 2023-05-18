package com.omh.android.maps.sample.start;

import static com.omh.android.maps.sample.utils.Constants.LAT_PARAM;
import static com.omh.android.maps.sample.utils.Constants.LNG_PARAM;

import android.content.Intent;
import android.os.Bundle;

import com.omh.android.maps.api.presentation.models.OmhCoordinate;
import com.omh.android.maps.sample.j.NavGraphDirections;
import com.omh.android.maps.sample.j.R;
import com.omh.android.maps.sample.j.databinding.ActivityMainBinding;
import com.omh.android.maps.sample.j.NavGraphDirections.ActionGlobalMapFragment;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent.getData() == null) return;
        String latitude = intent.getData().getQueryParameter(LAT_PARAM);
        String longitude = intent.getData().getQueryParameter(LNG_PARAM);
        if (latitude != null && longitude != null) {
            OmhCoordinate coordinate = new OmhCoordinate(Double.parseDouble(latitude), Double.parseDouble(longitude));
            ActionGlobalMapFragment action = NavGraphDirections.actionGlobalMapFragment();
            action.setCoordinate(coordinate);
            navController.navigate(action);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
