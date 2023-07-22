package com.indisparte.movie_details.fragments

import android.content.Intent
import android.net.Uri
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
import com.indisparte.model.entity.CountryResult
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Genre
import com.indisparte.model.entity.MovieDetails
import com.indisparte.movie_details.R
import com.indisparte.movie_details.adapter.CrewAdapter
import com.indisparte.movie_details.adapter.VideoAdapter
import com.indisparte.movie_details.databinding.CustomWatchProviderChipBinding
import com.indisparte.movie_details.databinding.FragmentMovieAboutBinding
import com.indisparte.movie_details.dialog.CollectionDialog
import com.indisparte.movie_details.fragments.base.ReleaseDateAdapter
import com.indisparte.network.Resource
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieAboutFragment : BaseFragment<FragmentMovieAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieAboutBinding
        get() = FragmentMovieAboutBinding::inflate

    private val viewModel: MovieDetailsViewModel by activityViewModels()
    private lateinit var chipGroupGenres: ChipGroup
    private lateinit var chipGroupWatchProviders: ChipGroup
    private val videoAdapter: VideoAdapter by lazy { VideoAdapter() }
    private val crewAdapter: CrewAdapter by lazy { CrewAdapter() }
    private val releaseDateAdapter: ReleaseDateAdapter by lazy { ReleaseDateAdapter() }
    private lateinit var providerChipLayoutParams: ViewGroup.LayoutParams
    private lateinit var collectionDialog: CollectionDialog
    override fun initializeViews() {
        chipGroupGenres = binding.genreChipGroup
        chipGroupWatchProviders = binding.justWatch.watchProviders
        binding.trailers.adapter = videoAdapter
        binding.recyclerviewCrew.adapter = crewAdapter
        binding.movieInfo.releaseInformationRecyclerview.adapter = releaseDateAdapter

        enableInnerScrollViewPager(binding.trailers)

        // Imposta la larghezza desiderata in pixel
        val desiredWidthInPixels = resources.getDimensionPixelSize(R.dimen.provider_chip_width)

        // Imposta l'altezza desiderata in pixel
        val desiredHeightInPixels =
            resources.getDimensionPixelSize(R.dimen.provider_chip_height)

        // Crea un nuovo oggetto LayoutParams con le dimensioni desiderate
        providerChipLayoutParams =
            ViewGroup.LayoutParams(desiredWidthInPixels, desiredHeightInPixels)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchMovieDetails()

        fetchVideos()

        fetchMovieCrew()

        fetchReleaseDates()

        fetchWatchProviders()


    }

    private fun fetchWatchProviders() {
        viewModel.watchProviders.collectIn(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    Timber.tag("MovieAboutFragment").e("Error: ${resource.error}")
                }

                is Resource.Loading -> {
                    Timber.tag("MovieAboutFragment").d("watch providers Loading...")
                }

                is Resource.Success -> {
                    val countryResult = resource.data
                    Timber.tag("MovieAboutFragment")
                        .d("Watch providers loaded! $countryResult")
                    binding.justWatch.root.visible()
                    binding.watchProviderTitle.visible()
                    countryResult?.let {
                        setWatchProvidersChipGroup(it)
                    }

                }

                null -> {
                    Timber.tag("MovieAboutFragment").e("Watch providers null result")
                    binding.justWatch.root.gone()
                    binding.watchProviderTitle.gone()
                }
            }
        }
    }

    private fun fetchReleaseDates() {
        viewModel.releaseDates.collectIn(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Error -> {
                    Timber.tag("MovieAboutFragment").e("Error: ${resources.error}")
                }

                is Resource.Loading -> {
                    Timber.tag("MovieAboutFragment").d("Release Dates Loading...")
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
                    Timber.tag("MovieAboutFragment").d("Crew Loading...")
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
                    Timber.tag("MovieAboutFragment").d("Videos Loading...")
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
                    Timber.tag("MovieAbout").d("Movie details loaded: $movieDetails")
                    //setup genre group
                    movieDetails?.let { setGenresChipGroup(it.genres) }
                }

                is Resource.Error -> {
                    val error = resources.error
                    Timber.tag("MovieAbout").e("Error-> $error")
                }

                is Resource.Loading -> {
                    Timber.tag("MovieAbout").d("Movie details Loading...")
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
                // TODO: Search movies with this genre id
                showToastMessage("Soon - Search ${chip.tag} id")
            }
            chipGroupGenres.addView(chip)
        }
    }

    private fun setWatchProvidersChipGroup(countryResult: CountryResult) {
        chipGroupWatchProviders.removeAllViews()
        val watchProviders = countryResult.allWatchProviders
        for (chipData in watchProviders) {
            val chipBinding: CustomWatchProviderChipBinding =
                CustomWatchProviderChipBinding.inflate(layoutInflater)

            chipBinding.watchProvider = chipData

            val chip = chipBinding.root

            chip.layoutParams = providerChipLayoutParams

            chip.setOnClickListener { chip ->
                // TODO: Open TMDB page
                showToastMessage("Soon - Open ${chipData.providerName}")
            }

            chipGroupWatchProviders.addView(chip)

        }
        binding.justWatch.root.setOnClickListener {
            openLinkInBrowser(countryResult.link)
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

    /**
     *  Returns a new list containing an even number of elements from the original list based on certain conditions.
     *
     *  If the original list is empty, it returns the same empty list.
     *
     *  If the original list contains 4 or more elements, it selects the first 4 elements from the list.
     *
     *  If the original list contains at least 2 elements but less than 4, it selects the first 2 elements.
     *
     *  If the original list contains less than 2 elements, it selects only the first element.
     *
     *  @return A new list containing an even number of elements from the original list.
     */
    private fun List<Crew>.takeEvenNumberOfItems(): List<Crew> {
        if (isEmpty())
            return this

        val numberOfItemsToSelect = when {
            size >= 4 -> 4 // If there are at least 4 items, select 4
            size >= 2 -> 2 // If there are at least 2 items, select 2
            else -> 1 // Otherwise, select only 1
        }

        // Take the first "numberOfItemsToSelect" elements from the list
        return take(numberOfItemsToSelect)
    }
    private fun openLinkInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


}

