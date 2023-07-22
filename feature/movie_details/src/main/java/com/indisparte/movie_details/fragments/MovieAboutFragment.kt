package com.indisparte.movie_details.fragments

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
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Genre
import com.indisparte.model.entity.MovieDetails
import com.indisparte.movie_details.adapter.CrewAdapter
import com.indisparte.movie_details.adapter.VideoAdapter
import com.indisparte.movie_details.databinding.FragmentMovieAboutBinding
import com.indisparte.movie_details.dialog.CollectionDialog
import com.indisparte.movie_details.fragments.base.ReleaseDateAdapter
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
    private val crewAdapter: CrewAdapter by lazy { CrewAdapter() }
    private val releaseDateAdapter: ReleaseDateAdapter by lazy { ReleaseDateAdapter() }
    private lateinit var collectionDialog: CollectionDialog
    override fun initializeViews() {
        chipGroupGenres = binding.genreChipGroup
        binding.trailers.adapter = videoAdapter
        binding.recyclerviewCrew.adapter = crewAdapter
        binding.movieInfo.releaseInformationRecyclerview.adapter = releaseDateAdapter

        enableInnerScrollViewPager(binding.trailers)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchMovieDetails()

        fetchVideos()

        fetchMovieCrew()

        viewModel.releaseDates.collectIn(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Error -> {
                    Timber.tag("MovieAboutFragment").e(resources.error?.message)
                }

                is Resource.Loading -> {
                    Timber.tag("MovieAboutFragment").d("Loading...")
                }

                is Resource.Success -> {
                    val releaseDates = resources.data
                    Timber.tag("MovieAboutFragment")
                        .d("Release Dates loaded! $releaseDates")
                    releaseDateAdapter.submitList(releaseDates)
                }

                null -> {
                    Timber.tag("MovieAboutFragment").e("Release Dates null result")
                }
            }
        }

    }

    private fun fetchMovieCrew() {
        viewModel.crew.collectIn(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Error -> {
                    Timber.tag("MovieAboutFragment").e(resources.error?.message)
                }

                is Resource.Loading -> {
                    Timber.tag("MovieAboutFragment").d("Loading...")
                }

                is Resource.Success -> {
                    val crewList = resources.data
                    Timber.tag("MovieAboutFragment").d("Crew loaded! ${crewList?.map { it.name }}")
                    crewAdapter.submitList(crewList?.takeEvenNumberOfItems())
                }

                null -> {
                    Timber.tag("MovieAboutFragment").e("Crew null result")
                }
            }
        }
    }

    private fun fetchVideos() {
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

            chip.setOnClickListener { chip ->
                showToastMessage("Soon - Search ${chip.tag} id")
            }

            chipGroupGenres.addView(chip)

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

private fun List<Crew>.takeEvenNumberOfItems(): List<Crew> {
    if (isEmpty())
        return this

    val numberOfItemsToSelect = when {
        size >= 4 -> 4 // Se ci sono almeno 4 oggetti, prendine 4
        size >= 2 -> 2 // Se ci sono almeno 2 oggetti, prendine 2
        else -> 1 // Altrimenti, prendine solo 1
    }

    // Prendi i primi "numberOfItemsToSelect" elementi dalla lista
    return take(numberOfItemsToSelect)
}
