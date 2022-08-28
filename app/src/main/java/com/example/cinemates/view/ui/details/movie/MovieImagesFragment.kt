package com.example.cinemates.view.ui.details.movie

import com.example.cinemates.adapter.ImageRecyclerViewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentMovieImagesBinding

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 09:54
 */
class MovieImagesFragment : Fragment() {
    private lateinit var mBinding: FragmentMovieImagesBinding
    private lateinit var mPosterAdapter: ImageRecyclerViewAdapter
    private lateinit var mBackdropAdapter: ImageRecyclerViewAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPosterAdapter = ImageRecyclerViewAdapter(context)
        mBackdropAdapter = ImageRecyclerViewAdapter(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieImagesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            postersRv.adapter = mPosterAdapter
            postersRv.setEmptyView(emptyBackdropView.root)
            backdropRv.adapter = mBackdropAdapter
            backdropRv.setEmptyView(emptyBackdropView.root)
        }

        viewModel.imagesResponse.observe(viewLifecycleOwner){ images->
            mPosterAdapter.addItems(images.posters)
            mBackdropAdapter.addItems(images.backdrops)
        }

    }
}