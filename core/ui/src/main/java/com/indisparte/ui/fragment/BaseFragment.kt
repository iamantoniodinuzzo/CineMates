package com.indisparte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


/**
 * This is a base fragment class for fragments that use view binding.
 * It is a generic class that takes in the type of the view binding.
 *
 * @param VB The type of the view binding
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    // The view binding for the fragment
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    /**
     * This abstract property is used to inflate the view binding for the fragment.
     */
    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    /**
     * This method is called when the fragment's view is being created.
     * It inflates the view binding using the bindingInflater property, and returns its root view.
     *
     * @param inflater The layout inflater to inflate the view binding
     * @param container The parent ViewGroup of the fragment's view
     * @param savedInstanceState The saved instance state of the fragment
     * @return The root view of the fragment's view binding
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        initializeViews() // Call the custom initialization function
        return binding.root
    }

    /**
     * This method shows a Snackbar with the provided message.
     *
     * @param message The message to display in the Snackbar
     */
    protected fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "" + message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    /**
     * This method shows a Toast message with the provided message.
     *
     * @param message The message to display in the Toast
     */
    protected fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /**
     * This method is called when the fragment's view is being destroyed.
     * It sets the view binding to null to prevent memory leaks.
     */
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    /**
     * This abstract method is called to initialize the views in the fragment.
     * Child classes must override this method to perform their specific initialization logic.
     */
    protected abstract fun initializeViews()

}