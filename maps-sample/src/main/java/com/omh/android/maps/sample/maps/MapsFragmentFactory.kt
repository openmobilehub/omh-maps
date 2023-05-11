package com.omh.android.maps.sample.maps

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.presentation.fragments.OmhMapFragment
import javax.inject.Inject

class MapsFragmentFactory @Inject constructor(
    private val omhMapProvider: OmhMapProvider
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            OmhMapFragment::class.java.name -> OmhMapFragment.newInstance(omhMapProvider)
            else -> return super.instantiate(classLoader, className)
        }
    }
}