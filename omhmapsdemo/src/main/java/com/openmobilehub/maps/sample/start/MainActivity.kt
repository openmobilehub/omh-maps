package com.openmobilehub.maps.sample.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openmobilehub.maps.sample.R
import com.openmobilehub.maps.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val initialFragment = InitialFragment.instantiate()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView_main, initialFragment)
            .commit()
    }
}
