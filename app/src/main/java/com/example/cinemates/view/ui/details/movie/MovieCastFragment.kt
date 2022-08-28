package com.example.cinemates.view.ui.details.movie

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import android.os.Bundle
import com.example.cinemates.util.ViewSize
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentMovieCastBinding
import com.example.cinemates.model.data.Cast

class MovieCastFragment : Fragment() {
    private lateinit var mBinding: FragmentMovieCastBinding
    private lateinit var mAdapter: ItemsRecyclerViewAdapter<Cast>
    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = ItemsRecyclerViewAdapter(ViewSize.LONG)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentMovieCastBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.castRecyclerView.adapter = mAdapter
        viewModel.cast.observe(viewLifecycleOwner) { cast ->
            mAdapter.addItems(cast.toMutableList())
        }
    }
}