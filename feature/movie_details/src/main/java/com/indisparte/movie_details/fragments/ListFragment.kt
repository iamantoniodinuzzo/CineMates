package com.indisparte.movie_details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.indisparte.movie_details.databinding.FragmentListingItemsBinding
import com.indisparte.ui.adapter.BaseAdapter
import com.indisparte.ui.fragment.BaseFragment

/**
 * A fragment in which a list of elements is shown.
 * @param T The type of objects contained in the recyclerview
 * @param A The adapter must extend [BaseAdapter]
 * @param adapter The adapter constructor
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class ListFragment<T, VB : ViewDataBinding, A : BaseAdapter<T, VB>>(val adapter: A) :
    BaseFragment<FragmentListingItemsBinding>() {
    override fun initializeViews() {
        binding.recyclerView.apply {
            // Set the RecyclerView to use a linear layout by default
            layoutManager = LinearLayoutManager(requireContext())
            this@apply.adapter = this@ListFragment.adapter
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListingItemsBinding
        get() = FragmentListingItemsBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addItemsToTheAdapter()
    }

    /**
     * This method is called to initialize the list in the fragment.
     * Child classes must override this method to provide the data for the list and perform any additional initialization.
     * Use the [adapter] to submit the list
     */
    protected abstract fun addItemsToTheAdapter()




}