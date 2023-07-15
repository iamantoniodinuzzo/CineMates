package com.indisparte.cinemates.ui.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.indisparte.cinemates.databinding.FragmentListingItemsBinding

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
class MediaImagesFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentListingItemsBinding? = null
    private val binding: FragmentListingItemsBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListingItemsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

    }

    private fun setupRecyclerView() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}