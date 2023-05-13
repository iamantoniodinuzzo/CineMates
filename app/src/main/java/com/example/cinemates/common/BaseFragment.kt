package com.example.cinemates.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.viewbinding.ViewBinding
import com.example.cinemates.R
import com.example.cinemates.util.getLong
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

/**
 * A generic base fragment class for handling view binding and common fragment operations.
 *
 * @param VB The episodeGroupType of the view binding for the fragment.
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: throw IllegalStateException("Fragment view not yet created.")

    /**
     * The inflater function to inflate the view binding for the fragment.
     */
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    /**
     * Sets up motion animations for the fragment.
     */
    private fun setupMotionAnimations() {
        enterTransition = MaterialFadeThrough()
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getLong(R.integer.material_motion_duration_long_2)
        }
    }

    /**
     * Called when the fragment is creating its view.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    /**
     * Called when the fragment is being destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Called when the fragment is being created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupMotionAnimations()
    }

    /**
     * Shows a Snack bar with a message.
     *
     * @param message The message to display.
     */
    protected fun showMessage(message: String?) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message ?: "",
            Snackbar.LENGTH_LONG
        ).show()
    }

    /**
     * Shows a Toast message with a message.
     *
     * @param message The message to display.
     */
    protected fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}