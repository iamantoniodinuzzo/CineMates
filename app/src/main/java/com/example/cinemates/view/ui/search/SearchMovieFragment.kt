package com.example.cinemates.view.ui.search

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import android.os.Bundle
import com.example.cinemates.util.ViewSize
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentSearchMovieBinding
import com.example.cinemates.model.data.Movie

class SearchMovieFragment : Fragment() {
    private lateinit var mBinding: FragmentSearchMovieBinding
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var mAdapter: ItemsRecyclerViewAdapter<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = ItemsRecyclerViewAdapter(ViewSize.SMALL)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentSearchMovieBinding.inflate(inflater, container, false)
        mBinding.apply {
            recyclerView.adapter = mAdapter
            recyclerView.setEmptyView(emptyView.root)
        }
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.queriedMovies.observe(viewLifecycleOwner) { movies ->
            mAdapter.addItems(movies.toMutableList())
        }

    }

}