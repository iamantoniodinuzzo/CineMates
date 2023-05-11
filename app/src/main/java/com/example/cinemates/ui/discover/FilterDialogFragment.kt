package com.example.cinemates.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.R
import com.example.cinemates.databinding.LayoutFilterDialogBinding
import com.example.cinemates.domain.model.common.Genre
import com.example.cinemates.domain.model.common.MediaFilter
import com.example.cinemates.domain.model.common.SortBy
import com.example.cinemates.util.MediaSortOption
import com.example.cinemates.util.MediaType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.indisparte.horizontalchipview.HorizontalChipView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class FilterDialogFragment : BottomSheetDialogFragment() {

    private var _binding: LayoutFilterDialogBinding? = null
    private val binding: LayoutFilterDialogBinding
        get() = _binding ?: throw IllegalStateException("Fragment view not yet created.")

    // Get a reference to the view model
    private val viewModel: DiscoverViewModel by activityViewModels()
    private lateinit var mediaFilterBuilder: MediaFilter.Builder
    private lateinit var selectedGenres: MutableSet<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaFilterBuilder = MediaFilter.Builder()
        selectedGenres = mutableSetOf()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using view binding
        _binding = LayoutFilterDialogBinding.inflate(inflater, container, false)
        viewModel.updateMediaFilter(mediaFilterBuilder)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            initMediaTypeToggleGroup()
            initSortChipGroup(view)
            initGenreChipGroup(view)
            initYearSpinner()
            initToggleGroupYearButton()

            saveButton.setOnClickListener {
                val movieFilter = mediaFilterBuilder.build()

                Toast.makeText(requireContext(), movieFilter.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun initMediaTypeToggleGroup() {
        val toggleGroup = binding.mediaTypeToggleGroup

        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.movie_button -> {
                        mediaFilterBuilder.mediaType(MediaType.MOVIE)
                        viewModel.updateMediaFilter(mediaFilterBuilder)
                    }

                    R.id.tv_button -> {
                        mediaFilterBuilder.mediaType( MediaType.TV)
                        viewModel.updateMediaFilter(mediaFilterBuilder)
                    }
                }
            }
        }
    }

    private fun initToggleGroupYearButton() {
        val toggleGroup = binding.yearFilter.toggleGroup

        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                val yearPicker = binding.yearFilter.yearPicker
                when (checkedId) {
                    R.id.any_button -> {
                        // Do something when "Any" button is selected
                        mediaFilterBuilder.year(null)
                        yearPicker.isVisible = false
                    }

                    R.id.one_year_button -> {
                        // Do something when "One Year" button is selected
                        yearPicker.isVisible = true
                    }
                }
            }
        }
    }

    private fun initYearSpinner() {
        // Find the NumberPicker view in the layout
        val yearPicker: NumberPicker = binding.yearFilter.yearPicker
        yearPicker.apply {
            minValue = 1943
            maxValue = Calendar.getInstance().get(Calendar.YEAR)
            value = Calendar.getInstance().get(Calendar.YEAR)
            setOnValueChangedListener { _, _, newVal ->
                mediaFilterBuilder.year(newVal)
            }
        }
    }

    private fun initGenreChipGroup(view: View) {
        //bind the custom view
        val chipGroup: HorizontalChipView<Genre> =
            view.findViewById<HorizontalChipView<Genre>>(R.id.genres)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.genres.collectLatest { genres ->
                chipGroup.setChipsList(
                    genres,//The list of items to be included in the chip group
                    textGetter = { genre -> genre.name }//The text to be included in the chip
                )
            }
        }

        chipGroup.onCheckedChangeListener = { _, genre, isChecked ->
            if (isChecked) {
                selectedGenres.add(genre.id)
            } else {
                selectedGenres.remove(genre.id)
            }
            mediaFilterBuilder.genresId(selectedGenres)
            viewModel.updateMediaFilter(mediaFilterBuilder)
        }

    }

    private fun initSortChipGroup(view: View) {
        //bind the custom view
        val chipGroup: HorizontalChipView<MediaSortOption> =
            view.findViewById<HorizontalChipView<MediaSortOption>>(R.id.sort_by)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sortByList.collectLatest { sortByList ->
                chipGroup.setChipsList(
                    sortByList,//The list of items to be included in the chip group
                    textGetter = { sortOption -> resources.getString(sortOption.nameResId) }//The text to be included in the chip
                )
            }
        }
        /*chipGroup.onCheckedChangeListener = { _, mediaSortOption, isChecked ->
            if (isChecked) {
                mediaFilterBuilder.sortBy(mediaSortOption)
            } else {
                mediaFilterBuilder.sortBy()//default
            }
            viewModel.updateMediaFilter(mediaFilterBuilder)
        }*/
        // specify the action on chips click
        chipGroup.onChipClicked = { sortOption ->
            mediaFilterBuilder.sortBy(sortOption)
            viewModel.updateMediaFilter(mediaFilterBuilder)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}