package com.indisparte.media_discover.filterable_fragment.movie.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.indisparte.filter.MediaDiscoverFilter
import com.indisparte.filter.MovieSortOptions
import com.indisparte.media_discover.R
import com.indisparte.media_discover.databinding.BottomSheetMovieFilterBinding
import com.indisparte.media_discover.databinding.CustomFilterChipBinding
import com.indisparte.media_discover.filterable_fragment.movie.FilterableMovieFragmentViewModel
import com.indisparte.network.whenResources
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import timber.log.Timber


@AndroidEntryPoint
class MovieFilterBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetMovieFilterBinding? = null
    private val binding get() = _binding!!
    private val LOG = Timber.tag(MovieFilterBottomSheet::class.java.simpleName)
    private val filterViewModel: MovieFilterViewModel by viewModels()
    private val filterableViewModel: FilterableMovieFragmentViewModel by navGraphViewModels(R.id.discover_graph) {
        defaultViewModelProviderFactory
    }
    private lateinit var badgeDrawable: BadgeDrawable
    private lateinit var latestAppliedFilter: MediaDiscoverFilter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetMovieFilterBinding.inflate(layoutInflater)

        initBadgeDrawable()


        updateLatestAppliedFilter()

        binding.btnApply.setOnClickListener {
            filterViewModel.applyFilters()

        }

        binding.btnResetAll.setOnClickListener {
            filterViewModel.resetFilters()
        }


        return binding.root
    }

    @androidx.annotation.OptIn(
        ExperimentalBadgeUtils::class
    )
    private fun initBadgeDrawable() {
        badgeDrawable = BadgeDrawable.createFromResource(requireContext(), R.xml.badge_style)
        badgeDrawable.isVisible = false
        badgeDrawable.badgeGravity = BadgeDrawable.TOP_END
        BadgeUtils.attachBadgeDrawable(badgeDrawable, binding.btnResetAll, binding.frameLayout)
    }

    private fun updateBadgeDrawable(counter: Int) {
        badgeDrawable.number = counter
        badgeDrawable.isVisible = counter != 0
    }

    /**
     * Updates the [latestAppliedFilter] based on the selected movie filter from the [filterableViewModel].
     * Also initializes the chip groups for sort types and genres.
     */
    private fun updateLatestAppliedFilter() {
        filterableViewModel.selectedMovieFilter.collectIn(viewLifecycleOwner) {
            latestAppliedFilter = it
            initChipGroupSortType()
            initChipGroupGenres()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterViewModel.uiState.map { it.filterCount }
            .collectIn(viewLifecycleOwner) { filterCounter ->
                updateBadgeDrawable(filterCounter)
            }


        filterViewModel.uiState.map { it.applyAllFilters }
            .collectIn(viewLifecycleOwner) { shouldApplyFilters ->
                if (shouldApplyFilters) {
                    val filterData = MediaDiscoverFilter(
                        sortBy = filterViewModel.uiState.value.sortType,
                        withGenresIds = filterViewModel.uiState.value.genresId
                    )
                    LOG.d("Apply this filter: $filterData")

                    filterableViewModel.updateFilter(filterData)

                    dismiss()
                }

            }

        filterViewModel.uiState.map { it.clearAllFilters }
            .collectIn(viewLifecycleOwner) { shouldResetFilters ->
                if (shouldResetFilters) {

                    LOG.d("Clear filter")

                    filterableViewModel.clearFilter()

                    dismiss()
                }

            }
    }

    /**
     * Initializes the chip group with genre options.
     */
    private fun initChipGroupGenres() {
        val chipGroup = binding.cgGenres
        chipGroup.removeAllViews()

        // Collect movie genres from the filterViewModel and populate chips accordingly
        filterViewModel.movieGenres.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = { genres ->
                    // Iterate through each genre and create corresponding chips
                    genres.forEach { genre ->
                        val chipBinding = CustomFilterChipBinding.inflate(layoutInflater)
                        val chip: Chip = chipBinding.root
                        chip.text = genre.name
                        chip.id = genre.id

                        // Personalize chip style here
                        chip.isClickable = true
                        chip.isCheckable = true
                        chipGroup.addView(chip)

                        // Handle chip selection
                        chip.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                LOG.d("$genre selected")
                                filterViewModel.selectGenre(genre.id)
                            } else {
                                LOG.d("$genre deselected")
                                filterViewModel.deselectGenre(genre.id)
                            }
                        }

                        // Set the chip's checked state based on the latest applied filter
                        chip.isChecked =
                            latestAppliedFilter.withGenresIds?.contains(chip.id) ?: false
                    }
                },
                onError = { exception ->
                    LOG.e("Error: ${exception?.message}")
                },
                onLoading = {
                    LOG.d("Loading genres...")
                }
            )
        }
    }


    /**
     * Initializes the chip group with sorting options.
     */
    private fun initChipGroupSortType() {
        val chipGroup = binding.cgSortTypes
        chipGroup.removeAllViews()

        // Iterate through each sorting option and create corresponding chips
        MovieSortOptions::class.nestedClasses.map { it.objectInstance as MovieSortOptions }
            .forEach { sortOption ->
                val chipBinding = CustomFilterChipBinding.inflate(layoutInflater)
                val chip: Chip = chipBinding.root
                chip.text = requireContext().getString(sortOption.sortName)
                chip.id = sortOption.id

                // Personalize chip style here
                chip.isClickable = true
                chip.isCheckable = true
                chipGroup.addView(chip)

                // Handle chip selection
                chip.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        LOG.d("Sort selected: ${chip.text}")
                        filterViewModel.setSortType(sortOption)
                    }
                }

                // Set the chip's checked state based on the latest applied filter
                chip.isChecked = (latestAppliedFilter.sortBy?.id == chip.id)
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
