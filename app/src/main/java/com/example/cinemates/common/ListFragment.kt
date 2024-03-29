package com.example.cinemates.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinemates.databinding.FragmentListingItemsBinding

/**
 * A fragment in which a list of elements is shown which have multiple views depending on the layout of the list.
 * @param T The episodeGroupType of objects contained in the recyclerview
 * @param A The adapter must extend [BaseAdapter]
 * @param adapter The adapter constructor
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class ListFragment<T, VB : ViewDataBinding, A : BaseAdapter<T>>(val adapter: A) :
    BaseFragment<FragmentListingItemsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListingItemsBinding
        get() = FragmentListingItemsBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.layoutRecyclerView.apply {
            // Set the RecyclerView to use a linear layout by default
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            recyclerView.adapter = adapter
        }
    }




}