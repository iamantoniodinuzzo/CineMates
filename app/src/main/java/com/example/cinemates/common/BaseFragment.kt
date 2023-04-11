package com.example.cinemates.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.cinemates.R
import com.example.cinemates.util.getLong
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }*/
        setupMotionAnimations()
    }

    private fun setupMotionAnimations() {
        enterTransition = MaterialFadeThrough()
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getLong(R.integer.material_motion_duration_long_2)
        }
    }
}