package com.indisparte.movie_details.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.indisparte.model.entity.CountryResult
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Genre
import com.indisparte.movie_details.R
import com.indisparte.movie_details.adapter.CrewGridAdapter
import com.indisparte.movie_details.adapter.ReleaseDateAdapter
import com.indisparte.movie_details.adapter.VideoAdapter
import com.indisparte.movie_details.databinding.CustomWatchProviderChipBinding
import com.indisparte.movie_details.databinding.FragmentMovieAboutBinding
import com.indisparte.movie_details.dialog.CollectionDialog
import com.indisparte.network.whenResources
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieAboutFragment : BaseFragment<FragmentMovieAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieAboutBinding
        get() = FragmentMovieAboutBinding::inflate

    private val viewModel: MovieDetailsViewModel by viewModels({ requireParentFragment() })
    private lateinit var chipGroupGenres: ChipGroup
    private lateinit var chipGroupWatchProviders: ChipGroup
    private lateinit var progressWatchProvider: CircularProgressIndicator
    private val videoAdapter: VideoAdapter by lazy { VideoAdapter() }
    private val crewAdapter: CrewGridAdapter by lazy { CrewGridAdapter() }
    private val releaseDateAdapter: ReleaseDateAdapter by lazy { ReleaseDateAdapter() }
    private lateinit var providerChipLayoutParams: ViewGroup.LayoutParams
    private lateinit var collectionDialog: CollectionDialog
    override fun initializeViews() {
        chipGroupGenres = binding.genreChipGroup
        chipGroupWatchProviders = binding.justWatch.watchProviders
        progressWatchProvider = binding.justWatch.progressIndicator
        binding.trailers.adapter = videoAdapter
        binding.gridCrew.adapter = crewAdapter
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
            resource?.whenResources(
                onSuccess = { countryResult ->
                    if (countryResult != null) {
                        Timber.tag("MovieAboutFragment").d("Watch providers loaded! $countryResult")
                        binding.justWatch.root.visible()
                        binding.watchProviderTitle.visible()
                        setWatchProvidersChipGroup(countryResult)
                        progressWatchProvider.hide()
                    } else {
                        Timber.tag("MovieAboutFragment").d("Watch providers is null. Hiding views.")
                        binding.justWatch.root.gone()
                        binding.watchProviderTitle.gone()
                    }
                },
                onError = { error ->
                    Timber.tag("MovieAboutFragment").e("Error: $error")
                    progressWatchProvider.hide()
                },
                onLoading = {
                    Timber.tag("MovieAboutFragment").d("Watch providers Loading...")
                    progressWatchProvider.show()
                }
            )
        }
    }


    private fun fetchReleaseDates() {
        viewModel.releaseDates.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { releaseDates ->
                    Timber.tag("MovieAboutFragment").d("Release Dates loaded! $releaseDates")
                    releaseDateAdapter.submitList(releaseDates)
                },
                onError = { error ->
                    Timber.tag("MovieAboutFragment").e("Error: $error")
                },
                onLoading = {
                    Timber.tag("MovieAboutFragment").d("Release Dates Loading...")
                }
            )
        }
    }


    private fun fetchMovieCrew() {
        viewModel.crew.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { crewList ->
                    Timber.tag("MovieAboutFragment").d("Crew loaded! ${crewList?.map { it.name }}")
                    crewAdapter.updateCrewList(crewList?.takeEvenNumberOfItems())
                },
                onError = { error ->
                    Timber.tag("MovieAboutFragment").e(error?.message)
                },
                onLoading = {
                    Timber.tag("MovieAboutFragment").d("Crew Loading...")
                }
            )
        }
    }

    private fun fetchVideos() {
        viewModel.videos.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { videos ->
                    Timber.tag("MovieAboutFragment").d("Videos loaded! $videos")
                    videoAdapter.submitList(videos)
                },
                onError = { error ->
                    Timber.tag("MovieAboutFragment").e(error?.message)
                },
                onLoading = {
                    Timber.tag("MovieAboutFragment").d("Videos Loading...")
                }
            )
        }
    }


    private fun fetchMovieDetails() {
        viewModel.selectedMovie.collectIn(viewLifecycleOwner) { resources ->
            resources.whenResources(
                onSuccess = { movieDetails ->
                    binding.movie = movieDetails
                    Timber.tag("MovieAbout").d("Movie details loaded: $movieDetails")
                    // Setup genre group
                    movieDetails?.let {
                        setGenresChipGroup(it.genres)
                    }
                },
                onError = { error ->
                    Timber.tag("MovieAbout").e("Error-> $error")
                },
                onLoading = {
                    Timber.tag("MovieAbout").d("Movie details Loading...")
                }
            )
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

