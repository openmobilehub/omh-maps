package com.github.omhmapsdemo.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.omhmapsdemo.R
import com.github.omhmapsdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val initialFragment = InitialFragment.instantiate()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView_main, initialFragment)
            .commit()
    }
}
