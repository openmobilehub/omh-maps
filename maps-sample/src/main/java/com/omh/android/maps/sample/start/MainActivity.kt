package com.omh.android.maps.sample.start

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.sample.NavGraphDirections
import com.omh.android.maps.sample.R
import com.omh.android.maps.sample.databinding.ActivityMainBinding
import com.omh.android.maps.sample.utils.Constants.LAT_PARAM
import com.omh.android.maps.sample.utils.Constants.LNG_PARAM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleIntent(intent)
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    @SuppressLint("MissingSuperCall") // Android error: https://issuetracker.google.com/issues/67035929
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.data?.let { uri: Uri ->
            val latitude = uri.getQueryParameter(LAT_PARAM)
            val longitude = uri.getQueryParameter(LNG_PARAM)
            if (latitude != null && longitude != null) {
                val coordinate = OmhCoordinate(latitude.toDouble(), longitude.toDouble())
                val action = NavGraphDirections.actionGlobalMapFragment(coordinate)
                findNavController(R.id.nav_host_fragment_content_main).navigate(action)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
