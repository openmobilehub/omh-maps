package com.openmobilehub.maps.sample.start

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.openmobilehub.maps.sample.databinding.FragmentInitialBinding
import com.openmobilehub.maps.sample.maps.MapActivity

class InitialFragment : Fragment() {

    private var _binding: FragmentInitialBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun instantiate(): InitialFragment {
            return InitialFragment()
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

        binding.buttonFirst.setOnClickListener {
            val intent = Intent(activity, MapActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
