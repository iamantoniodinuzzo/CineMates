package com.indisparte.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.indisparte.model.entity.Genre
import com.indisparte.model.entity.MovieDetails
import com.indisparte.movie_details.adapter.VideoAdapter
import com.indisparte.movie_details.databinding.FragmentMovieAboutBinding
import com.indisparte.network.Resource
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieAboutFragment : BaseFragment<FragmentMovieAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieAboutBinding
        get() = FragmentMovieAboutBinding::inflate

    private val viewModel: MovieDetailsViewModel by activityViewModels()
    private lateinit var chipGroupGenres: ChipGroup
    private val videoAdapter: VideoAdapter by lazy { VideoAdapter() }
    private lateinit var collectionDialog: CollectionDialog
    override fun initializeViews() {
        //Nothing
        chipGroupGenres = binding.genreChipGroup
        binding.trailers.adapter = videoAdapter
        enableInnerScrollViewPager(binding.trailers)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchMovieDetails()

        viewModel.videos.collectIn(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Error -> {
                    Timber.tag("MovieAboutFragment").e(resources.error?.message)
                }

                is Resource.Loading -> {
                    Timber.tag("MovieAboutFragment").d("Loading...")
                }

                is Resource.Success -> {
                    val videos = resources.data
                    Timber.tag("MovieAboutFragment").d("Videos loaded! $videos")
                    videoAdapter.submitList(videos)
                }

                null -> {
                    Timber.tag("MovieAboutFragment").e("Videos null result")
                }
            }
        }


    }

    private fun fetchMovieDetails() {
        viewModel.selectedMovie.collectIn(
            viewLifecycleOwner,
        ) { resources ->
            when (resources) {
                is Resource.Success -> {
                    val movieDetails: MovieDetails? = resources.data
                    binding.movie = movieDetails

                    //setup genre group
                    movieDetails?.let { setGenresChipGroup(it.genres) }
                }

                is Resource.Error -> {
                    val error = resources.error
                    Timber.tag("MovieAbout").e("Cannot load content. Error-> $error")
                }

                is Resource.Loading -> {
                    Timber.tag("MovieAbout").d("Loading content...")
                }
            }
        }
    }

    private fun setGenresChipGroup(genres: List<Genre>) {
        chipGroupGenres.removeAllViews()
        for (chipData in genres) {
            val chip = Chip(chipGroupGenres.context)

            chip.text = chipData.name

            chip.tag = chipData.id

            chipGroupGenres.addView(chip)

            chip.setOnClickListener { chip ->
                showToastMessage("Soon - Search ${chip.tag} id")
            }

        }
    }

    // Disable ViewPager2 from intercepting touch events of RecyclerView
    private fun enableInnerScrollViewPager(recyclerView: RecyclerView) {
        recyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.actionMasked
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Disallow ViewPager2 to intercept touch events of RecyclerView
                        rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                }
                return false
            }
        })
    }

}
