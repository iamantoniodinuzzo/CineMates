package com.example.cinemates.view.ui.details.movie

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import com.example.cinemates.adapter.YoutubeVideoRecyclerViewAdapter
import android.os.Bundle
import com.example.cinemates.util.ViewSize
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentMovieInfoBinding
import com.example.cinemates.model.data.Movie

class MovieInfoFragment : Fragment() {
    private lateinit var mBinding: FragmentMovieInfoBinding
    private lateinit var mMoviesSimilarAdapter: ItemsRecyclerViewAdapter<Movie>
    private lateinit var mVideoAdapter: YoutubeVideoRecyclerViewAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMoviesSimilarAdapter = ItemsRecyclerViewAdapter(ViewSize.SMALL)
        mVideoAdapter = YoutubeVideoRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            recommendedRecyclerView.adapter = mMoviesSimilarAdapter
            recommendedRecyclerView.setEmptyView(emptyViewRecommended.root)
            videosRecyclerView.adapter = mVideoAdapter

            collectionName.collectionName.setOnClickListener {
                val fm = requireActivity().supportFragmentManager
                val collectionDialogFragment = CollectionDialogFragment()
                collectionDialogFragment.show(fm, "fragment_show_collection")
            }

            fab.setOnClickListener {
                // TODO: 22/08/2022 open dialog to choose a list
            }
            viewModel.selectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
                movie = selectedMovie
            }
            viewModel.videos.observe(viewLifecycleOwner){videos->
                if(videos.isEmpty()){
                    textSectionVideo.visibility = View.GONE
                }else{
                    textSectionVideo.visibility = View.VISIBLE
                    mVideoAdapter.setDataList(videos)
                }
            }
        }

        viewModel.similarMovies.observe(viewLifecycleOwner) { similarMovies ->
            mMoviesSimilarAdapter.addItems(similarMovies.toMutableList())
        }

    }


}