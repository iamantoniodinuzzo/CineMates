package com.indisparte.media_discover.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.indisparte.discover.util.MediaDiscoverFilter
import com.indisparte.discover.util.SortOptions
import com.indisparte.media_discover.R
import com.indisparte.media_discover.custom_filters.FilterableFragmentViewModel
import com.indisparte.media_discover.databinding.BottomSheetFilterBinding
import com.indisparte.media_discover.databinding.CustomFilterChipBinding
import com.indisparte.network.whenResources
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import timber.log.Timber

@AndroidEntryPoint
class FilterBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetFilterBinding? = null
    private val binding get() = _binding!!
    private val LOG = Timber.tag(FilterBottomSheet::class.java.simpleName)
    private val filterViewModel: FilterViewModel by viewModels()
    private val filterableViewModel: FilterableFragmentViewModel  by navGraphViewModels(R.id.discover_graph){
        defaultViewModelProviderFactory
    }
    private var badgeDrawable: BadgeDrawable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetFilterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChipGroupSortType()

        initChipGroupGenres()

        binding.btnApply.setOnClickListener {
            filterViewModel.applyFilters()

        }

        binding.btnResetAll.setOnClickListener {
            filterViewModel.resetFilters()
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

    private fun initChipGroupGenres() {
        val chipGroup = binding.cgGenres
        chipGroup.removeAllViews()
        filterViewModel.movieGenres.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = { genres ->
                    genres.forEach { genre ->
                        val chipBinding = CustomFilterChipBinding.inflate(layoutInflater)
                        val chip: Chip = chipBinding.root
                        chip.text = genre.name
                        chip.tag = genre.id

                        // Personalizza lo stile delle chips qui
                        chip.isClickable = true
                        chip.isCheckable = true
                        chipGroup.addView(chip)

                        // Gestione della selezione della chip
                        chip.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                LOG.d("$genre selected")
                                filterViewModel.selectGenre(genre.id)
                            } else {
                                LOG.d("$genre deselected")
                                filterViewModel.deselectGenre(genre.id)
                            }

                        }
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

    private fun initChipGroupSortType() {
        val chipGroup = binding.cgSortTypes
        chipGroup.removeAllViews()
        SortOptions.values().forEachIndexed { index, sortOption ->
            val chipBinding = CustomFilterChipBinding.inflate(layoutInflater)
            val chip: Chip = chipBinding.root
            chip.text = requireContext().getString(sortOption.sortName)

            // Personalizza lo stile delle chips qui
            chip.isClickable = true
            chip.isCheckable = true
            chipGroup.addView(chip)

            // Gestione della selezione della chip
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    LOG.d("Sort selected: ${chip.text}")
                    filterViewModel.setSortType(sortOption.descendingOrder)
                    // Qui puoi eseguire l'azione desiderata in base alla chip selezionata
                    // Ad esempio, ordine ascendente o discendente

                    /*val selectedOrder = if (ascendingOrderSelected) {
                        sortOption.ascendingOrder
                    } else {
                        sortOption.descendingOrder
                    }*/
                    // Esegui l'azione desiderata utilizzando l'ordine selezionato
                }
            }

            // Imposta lo stato iniziale della chip "Popularity" come selezionato
            chip.isChecked = index == SortOptions.POPULARITY.ordinal
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
