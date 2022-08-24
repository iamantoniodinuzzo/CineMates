package com.example.cinemates.view.ui.search

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import com.example.cinemates.model.data.Cast
import android.os.Bundle
import com.example.cinemates.util.ViewSize
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentSearchActorBinding

class SearchActorFragment : Fragment() {
    private lateinit var mBinding: FragmentSearchActorBinding
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var mRecyclerViewAdapter: ItemsRecyclerViewAdapter<Cast>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRecyclerViewAdapter = ItemsRecyclerViewAdapter(ViewSize.LONG)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentSearchActorBinding.inflate(inflater, container, false)
        mBinding.apply {
            recyclerView.adapter = mRecyclerViewAdapter
            recyclerView.setEmptyView(emptyView.root)
        }

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.queriedActors.observe(viewLifecycleOwner) { persons ->
            mRecyclerViewAdapter.addItems(persons.toMutableList())
        }

    }
}