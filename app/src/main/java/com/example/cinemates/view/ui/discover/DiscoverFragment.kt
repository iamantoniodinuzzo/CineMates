package com.example.cinemates.view.ui.discover

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentDiscoverBinding
import com.example.cinemates.model.data.Filter
import com.example.cinemates.util.randomColor
import com.google.android.material.chip.Chip
import com.google.android.material.transition.MaterialFadeThrough

class DiscoverFragment : Fragment() {
    private var _binding: FragmentDiscoverBinding? = null
    private val binding: FragmentDiscoverBinding
        get() = _binding!!
    private val genreMap: HashMap<Int, String>
        get() {
            val genreMap = HashMap<Int, String>()
            genreMap[28] = "Action"
            genreMap[12] = "Adventure"
            genreMap[16] = "Animation"
            genreMap[35] = "Comedy"
            genreMap[80] = "Crime"
            genreMap[99] = "Documentary"
            genreMap[18] = "Drama"
            genreMap[10751] = "Family"
            genreMap[14] = "Fantasy"
            genreMap[36] = "History"
            genreMap[27] = "Horror"
            genreMap[10402] = "Music"
            genreMap[9648] = "Mystery"
            genreMap[10749] = "Romance"
            genreMap[878] = "Science Fiction"
            genreMap[53] = "Thriller"
            genreMap[10752] = "War"
            genreMap[37] = "Western"
            genreMap[10770] = "TV Movie"
            return genreMap
        }
    private val discoverViewModel: DiscoverViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupMotionAnimations()
    }

    private fun setupMotionAnimations() {
        enterTransition = MaterialFadeThrough()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateChipGroup()

        binding.apply {

            searchButton.setOnClickListener { view -> findNavController(view).navigate(R.id.action_discoverFragment_to_searchFragment) }
            chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                if (checkedIds.isNotEmpty()) {
                    buttonGroup.visibility = View.VISIBLE
                    discoverViewModel.updateSelectedGenres(checkedIds.toString())
                } else {
                    buttonGroup.visibility = View.GONE
                }
            }

            applyFilterBtn.setOnClickListener {
                val filter = Filter(Filter.Sort.POPULARITY, discoverViewModel.selectedGenres.value)
                val action =
                    DiscoverFragmentDirections.actionDiscoverFragmentToFilterFragment(filter)
                findNavController(view).navigate(action)
            }
            cleanFilters.setOnClickListener{
                chipGroup.clearCheck()
                discoverViewModel.initGenres()
            }
        }

    }

    private fun populateChipGroup() {
        val chipGroup = binding.chipGroup
        chipGroup.removeAllViews()
        for ((key, value) in genreMap) {
            val genreChip = Chip(context)
            genreChip.id = key
            genreChip.isCheckable = true
            genreChip.text = value
            genreChip.setTextColor(Color.WHITE)
            genreChip.chipBackgroundColor = ColorStateList.valueOf(randomColor)
           /* genreChip.setOnClickListener { view ->
                val selectedGenre = Genre(key, value, false)
                discoverViewModel.updateSelectedGenres(selectedGenre)
            }*/

            chipGroup.addView(genreChip)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}