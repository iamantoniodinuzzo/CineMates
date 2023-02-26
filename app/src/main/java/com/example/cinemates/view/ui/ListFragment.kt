package com.example.cinemates.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.cinemates.view.ui.adapter.MultipleViewSizeAdapter
import com.example.cinemates.databinding.FragmentListingItemsBinding
import com.example.cinemates.view.ui.adapter.BaseAdapter

/**
 * A fragment in which a list of elements is shown which have multiple views depending on the layout of the list.
 * @param T The type of objects contained in the recyclerview
 * @param A The adapter must extend [MultipleViewSizeAdapter]
 * @param adapter The adapter constructor
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class ListFragment<T,VB : ViewDataBinding, A : BaseAdapter<T, VB>>(val adapter: A) :
    Fragment() {
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
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(emptyView.root)
            counter = adapter.itemCount
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}