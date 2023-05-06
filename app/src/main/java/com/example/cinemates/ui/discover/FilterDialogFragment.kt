package com.example.cinemates.ui.discover

import android.os.Bundle
import android.util.Log
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
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.MovieSort
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.indisparte.horizontalchipview.HorizontalChipView
import kotlinx.coroutines.flow.collectLatest
import java.util.*

private val TAG = FilterDialogFragment::class.simpleName

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class FilterDialogFragment : BottomSheetDialogFragment() {

    private var _binding: LayoutFilterDialogBinding? = null
    private val binding: LayoutFilterDialogBinding
        get() = _binding ?: throw IllegalStateException("Fragment view not yet created.")

    // Get a reference to the view model
    private val viewModel: DiscoverViewModel by activityViewModels()
    private lateinit var mMediaFilterBuilder: MediaFilter.Builder
    private lateinit var genres: MutableSet<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMediaFilterBuilder = MediaFilter.Builder(MediaType.MOVIE)
        genres = mutableSetOf()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using view binding
        _binding = LayoutFilterDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            initMediaTypeToggleGroup(view)
            initMovieSortChipGroup(view)
            initGenreChipGroup(view)
            initYearSpinner()
            initToggleGroupYearButton()

            applyButton.setOnClickListener {
                val genreList = this@FilterDialogFragment.genres.toList()
                Log.d(TAG, "genreList: $genreList")
                mMediaFilterBuilder.genresId(genreList)
                val movieFilter = mMediaFilterBuilder.build()

                Toast.makeText(requireContext(), movieFilter.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun initMediaTypeToggleGroup(view: View) {
        val toggleGroup = binding.mediaTypeToggleGroup

        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.movie_button -> {
                        mMediaFilterBuilder = MediaFilter.Builder(MediaType.MOVIE)
                    }
                    R.id.tv_button -> {
                        mMediaFilterBuilder = MediaFilter.Builder(MediaType.MOVIE)
                    }
                }
            }
        }
    }

    private fun initToggleGroupYearButton() {
        val toggleGroup = binding.yearFilter.toggleGroup

        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.any_button -> {
                        // Do something when "Any" button is selected
                        mMediaFilterBuilder.year(null)
                        binding.yearFilter.yearPicker.isVisible = false
                    }
                    R.id.one_year_button -> {
                        // Do something when "One Year" button is selected
                        binding.yearFilter.yearPicker.isVisible = true
                    }
                }
            }
        }
    }

    private fun initYearSpinner() {
        // Find the NumberPicker view in the layout
        val yearPicker: NumberPicker = binding.yearFilter.yearPicker

        // Set the minimum and maximum values for the NumberPicker
        yearPicker.minValue = 1943
        yearPicker.maxValue = Calendar.getInstance().get(Calendar.YEAR)

        // Set the default value for the NumberPicker
        yearPicker.value = Calendar.getInstance().get(Calendar.YEAR)

        // Set the OnValueChangedListener for the NumberPicker
        yearPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            // Do something with the selected year
            mMediaFilterBuilder.year(newVal)
        }
    }

    private fun initGenreChipGroup(view: View) {
        //bind the custom view
        val chipGroup: HorizontalChipView<Genre> =
            view.findViewById<HorizontalChipView<Genre>>(R.id.genres)

        chipGroup.chipLayout = R.style.CustomFilterChipStyle
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movieGenres.collectLatest { genreList ->
                genreList?.let {
                    chipGroup.setChipsList(
                        it,//The list of items to be included in the chip group
                        textGetter = { genre -> genre.name }//The text to be included in the chip
                    )
                }
            }
        }

        // specify the action on chips click
        chipGroup.onChipClicked = { genre ->
            genres.add(genre.id)
        }
    }

    private fun initMovieSortChipGroup(view: View) {
        //bind the custom view
        val chipGroup: HorizontalChipView<MovieSort> =
            view.findViewById<HorizontalChipView<MovieSort>>(R.id.sort_by)

        chipGroup.chipLayout = R.style.CustomFilterChipStyle

        //set all the elements
        chipGroup.setChipsList(
            MovieSort.values().asList(),//The list of items to be included in the chip group
            textGetter = { sort -> resources.getString(sort.nameResId) }//The text to be included in the chip
        )

        // specify the action on chips click
        chipGroup.onChipClicked = { sortBy ->
            mMediaFilterBuilder.sortBy(sortBy)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}