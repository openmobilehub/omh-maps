package com.omh.android.maps.sample.start

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.sample.databinding.FragmentInitialBinding
import com.omh.android.maps.sample.maps.MapActivity
import com.omh.android.maps.sample.utils.Constants.PERMISSIONS

class InitialFragment : Fragment() {

    private var _binding: FragmentInitialBinding? = null
    private val binding get() = _binding!!
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            binding.textViewCoordinate.text = getOmhCoordinateFromIntent(result.data).toString()
        }
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val allPermissionsGranted = permissions.all { it.value }
        if (allPermissionsGranted) {
            goToMap()
        }
    }

    private fun getOmhCoordinateFromIntent(intent: Intent?): OmhCoordinate? {
        val coordinateResult: OmhCoordinate? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getParcelableExtra(LOCATION_RESULT, OmhCoordinate::class.java)
            } else {
                // Before Android 13, API level 33(Tiramisu) use: fun <T : Parcelable?> getParcelableExtra(name: String?): T
                @Suppress("DEPRECATION")
                intent?.getParcelableExtra(LOCATION_RESULT)
            }
        return coordinateResult
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInitialBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            permissionLauncher.launch(PERMISSIONS)
        }
    }

    private fun goToMap() {
        val intent = Intent(activity, MapActivity::class.java)
        startForResult.launch(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val LOCATION_RESULT = "LOCATION_RESULT"
        fun instantiate(): InitialFragment {
            return InitialFragment()
        }
    }
}
