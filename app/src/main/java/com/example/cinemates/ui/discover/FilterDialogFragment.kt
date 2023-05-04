package com.example.cinemates.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.R
import com.example.cinemates.databinding.LayoutFilterDialogBinding
import com.example.cinemates.domain.model.common.Genre
import com.example.cinemates.util.MovieSort
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.indisparte.horizontalchipview.HorizontalChipView
import kotlinx.coroutines.flow.collectLatest

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class FilterDialogFragment : BottomSheetDialogFragment() {

    private var _binding: LayoutFilterDialogBinding? = null
    private val binding: LayoutFilterDialogBinding
        get() = _binding ?: throw IllegalStateException("Fragment view not yet created.")

    // Get a reference to the view model
    private val viewModel: DiscoverViewModel by activityViewModels()

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
            initMovieSortChipGroup(view)
            initGenreChipGroup(view)
        }

    }

    private fun initGenreChipGroup(view: View) {
        //bind the custom view
        val chipGroup: HorizontalChipView<Genre> =
            view.findViewById<HorizontalChipView<Genre>>(R.id.genres)

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
            Toast.makeText(
                requireContext(),
                "Add $genre to filter",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initMovieSortChipGroup(view: View) {
        //bind the custom view
        val chipGroup: HorizontalChipView<MovieSort> =
            view.findViewById<HorizontalChipView<MovieSort>>(R.id.sort_by)

        //set all the elements
        chipGroup.setChipsList(
            MovieSort.values().asList(),//The list of items to be included in the chip group
            textGetter = { sort -> resources.getString(sort.nameResId) }//The text to be included in the chip
        )

        // specify the action on chips click
        chipGroup.onChipClicked = { sortBy ->
            Toast.makeText(
                requireContext(),
                "Add $sortBy to filter",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}