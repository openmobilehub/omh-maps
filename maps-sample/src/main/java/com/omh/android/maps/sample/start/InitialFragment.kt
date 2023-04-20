package com.omh.android.maps.sample.start

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.omh.android.maps.api.presentation.models.OmhCoordinate
import com.omh.android.maps.sample.R
import com.omh.android.maps.sample.databinding.FragmentInitialBinding
import com.omh.android.maps.sample.utils.Constants.AUTHORITY_URL
import com.omh.android.maps.sample.utils.Constants.LAT_PARAM
import com.omh.android.maps.sample.utils.Constants.LNG_PARAM
import com.omh.android.maps.sample.utils.Constants.PATH
import com.omh.android.maps.sample.utils.Constants.PERMISSIONS
import com.omh.android.maps.sample.utils.Constants.SCHEME_PROTOCOL
import com.omh.android.maps.sample.utils.Constants.SHARE_TITLE
import com.omh.android.maps.sample.utils.Constants.TYPE_TEXT_PLAIN

class InitialFragment : Fragment() {

    private var _binding: FragmentInitialBinding? = null
    private val binding get() = _binding!!

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val allPermissionsGranted = permissions.all { it.value }
        if (allPermissionsGranted) {
            findNavController().navigate(R.id.action_initialFragment_to_mapFragment)
        }
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

        arguments?.let {
            val coordinate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(LOCATION_RESULT, OmhCoordinate::class.java)
            } else {
                // Before Android 13, API level 33(Tiramisu) use: fun <T : Parcelable?> getParcelable(name: String?): T
                @Suppress("DEPRECATION")
                it.getParcelable(LOCATION_RESULT)
            }
            coordinate?.let {
                binding.textViewCoordinate.text = coordinate.toString()
                binding.buttonShareLocation.visibility = View.VISIBLE
                binding.buttonShareLocation.setOnClickListener {
                    intentSend(coordinate)
                }
            }
        }
        binding.buttonFirst.setOnClickListener {
            permissionLauncher.launch(PERMISSIONS)
        }
    }

    private fun intentSend(coordinate: OmhCoordinate) {
        val intent = Intent(Intent.ACTION_SEND)
        val uriBuilder = Uri.Builder()
            .scheme(SCHEME_PROTOCOL)
            .authority(AUTHORITY_URL)
            .appendPath(PATH)
            .appendQueryParameter(LAT_PARAM, coordinate.latitude.toString())
            .appendQueryParameter(LNG_PARAM, coordinate.longitude.toString())
        intent.type = TYPE_TEXT_PLAIN
        intent.putExtra(Intent.EXTRA_TEXT, uriBuilder.build().toString())
        startActivity(Intent.createChooser(intent, SHARE_TITLE))
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
