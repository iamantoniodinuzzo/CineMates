package com.indisparte.movie_details.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import com.github.ajalt.timberkt.Timber
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.indisparte.common.CountryResult
import com.indisparte.common.Genre
import com.indisparte.movie_details.R
import com.indisparte.movie_details.adapter.CrewAdapter
import com.indisparte.movie_details.adapter.ReleaseDateAdapter
import com.indisparte.movie_details.adapter.VideoAdapter
import com.indisparte.movie_details.databinding.CustomWatchProviderChipBinding
import com.indisparte.movie_details.databinding.FragmentMovieAboutBinding
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.whenResources
import com.indisparte.person.Crew
import com.indisparte.ui.databinding.LayoutChoiceChipBinding
import com.indisparte.ui.databinding.LayoutFavoriteChipBinding
import com.indisparte.ui.dialog.DialogHelper
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import com.indisparte.util.extension.enableInnerScrollViewPager
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible

class MovieAboutFragment : BaseFragment<FragmentMovieAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieAboutBinding
        get() = FragmentMovieAboutBinding::inflate

    private val LOG = Timber.tag(MovieAboutFragment::class.java.simpleName)

    private val viewModel: MovieDetailsViewModel by viewModels({ requireParentFragment() })
    private lateinit var chipGroupGenres: ChipGroup
    private lateinit var chipGroupWatchProviders: ChipGroup
    private lateinit var progressWatchProvider: CircularProgressIndicator
    private val videoAdapter: VideoAdapter by lazy { VideoAdapter() }
    private val crewAdapter: CrewAdapter by lazy { CrewAdapter() }
    private val releaseDateAdapter: ReleaseDateAdapter by lazy { ReleaseDateAdapter() }
    private lateinit var providerChipLayoutParams: ViewGroup.LayoutParams
    override fun initializeViews() {
        chipGroupGenres = binding.genreChipGroup
        chipGroupWatchProviders = binding.justWatch.watchProviders
        progressWatchProvider = binding.justWatch.progressIndicator
        binding.trailers.adapter = videoAdapter
        binding.trailers.enableInnerScrollViewPager()
        binding.gridCrew.adapter = crewAdapter
        crewAdapter.setOnItemClickListener { crew ->
            val activity = requireActivity()
            if (activity is ToFlowNavigable) {
                activity.navigateToFlow(NavigationFlow.PersonDetailsFlow(crew.id))
            }
        }
        binding.movieInfo.releaseInformationRecyclerview.adapter = releaseDateAdapter

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

        fetchMovieInfo()

        binding.movieInfo.linearLayoutMovieInfo.hideIfVisibleViewsArLessThan(2)

    }

    private fun fetchMovieInfo() {
        viewModel.movieInfo.collectIn(viewLifecycleOwner) { resource ->
            resource?.whenResources(
                onSuccess = {
                    LOG.d("Loaded all details")
//                    Load movie details
                    binding.movie = it.movieDetails
                    // Setup genre group
                    setGenresChipGroup(it.movieDetails.genres)
//                    Load watch provider
                    setWatchProvidersChipGroup(it.watchProvider)
//                    Load release dates
                    releaseDateAdapter.submitList(it.releaseDates)
//                    Load crew
                    crewAdapter.submitList(it.crew.takeEvenNumberOfItems())
                    binding.crewTitle.apply {
                        if (it.crew.isEmpty()) gone() else visible()
                    }
//                    Load videos
                    videoAdapter.submitList(it.videos)
                    binding.trailerTitle.apply {
                        if (it.videos.isEmpty()) gone() else visible()
                    }

                },
                onError = { exception ->
                    val message = requireContext().getString(exception.messageRes)
                    LOG.e("An error occurred $message")
                },
                onLoading = {
                    LOG.d("Loading movie info...")

                }
            )
        }
    }


    private fun setGenresChipGroup(genres: List<Genre>) {
        if (genres.isEmpty()) {
            binding.genresTitle.gone()
            return
        }
        binding.genresTitle.visible()
        chipGroupGenres.removeAllViews()

        for (chipData in genres) {
            val chipBinding = if (!chipData.isFavorite)
                LayoutChoiceChipBinding.inflate(layoutInflater)
            else
                LayoutFavoriteChipBinding.inflate(layoutInflater)

            val chip: Chip = chipBinding.root as Chip

            chip.text = chipData.name
            chip.tag = chipData.id

            chip.setOnLongClickListener {
                updateChipStatusAndLayout(chipData, chip, genres)
                true
            }

            chipGroupGenres.addView(chip)
        }
    }

    private fun updateChipStatusAndLayout(
        chipData: Genre,
        chip: Chip,
        genres: List<Genre>,
    ) {

        val (title, message) = if (chipData.isFavorite) {
            Pair(
                getString(R.string.dialog_title_remove_from_favorites),
                getString(
                    R.string.dialog_message_remove_from_favorite,
                    chip.text
                )
            )
        } else {
            Pair(
                getString(R.string.dialog_title_set_as_favorite),
                getString(
                    R.string.dialog_message_set_as_favorite,
                    chip.text
                )
            )
        }
        val dialogHelper = DialogHelper(requireContext())
        val dialog = dialogHelper.createConfirmationDialog(
            title,
            message = message,
            positiveButtonText = getString(R.string.positive_btn_text),
            negativeButtonText = getString(R.string.negative_btn_text),
            positiveAction = {
                chipData.isFavorite = !chipData.isFavorite

                viewModel.updateGenre(chipData)


                // Remove the existing Chip
                chipGroupGenres.removeView(chip)

                // Recursive call to recreate the Chip with the updated layout
                setGenresChipGroup(genres)
            },
            negativeAction = {
                // Do nothing or you can handle additional action
            }
        )
        dialog.show()
    }

    private fun setWatchProvidersChipGroup(countryResult: CountryResult?) {
        if (countryResult != null) {
            binding.justWatch.root.visible()
            binding.watchProviderTitle.visible()
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
            progressWatchProvider.hide()
        } else {
            binding.justWatch.root.gone()
            binding.watchProviderTitle.gone()
        }
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

    private fun LinearLayoutCompat.hideIfVisibleViewsArLessThan(visibleViewLimit: Int) {
        var visibleViews = 0

        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view.visibility == View.VISIBLE) {
                visibleViews++
            }
        }

        if (visibleViews <= visibleViewLimit) gone() else visible()


    }


}

