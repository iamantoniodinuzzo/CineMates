package com.example.cinemates.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinemates.databinding.FragmentListingItemsBinding
import com.example.cinemates.common.BaseAdapter
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.common.BaseFragment

/**
 * A fragment in which a list of elements is shown which have multiple views depending on the layout of the list.
 * @param T The type of objects contained in the recyclerview
 * @param A The adapter must extend [SingleViewAdapter]
 * @param adapter The adapter constructor
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class ListFragment<T, VB : ViewDataBinding, A : BaseAdapter<T>>(val adapter: A) :
    BaseFragment() {
    private var _binding: FragmentListingItemsBinding? = null
    protected val binding: FragmentListingItemsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListingItemsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.apply {
            // Set the RecyclerView to use a linear layout by default
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
            binding.recyclerView.adapter = adapter
            counter = adapter.itemCount
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}