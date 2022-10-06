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
import com.google.android.material.chip.Chip
import com.google.android.material.transition.MaterialFadeThrough

class DiscoverFragment : Fragment() {
    private var _binding: FragmentDiscoverBinding? = null
    private val binding: FragmentDiscoverBinding
        get() = _binding!!

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
            cleanFilters.setOnClickListener {
                chipGroup.clearCheck()
                discoverViewModel.initGenres()
            }
        }

    }

    private fun populateChipGroup() {
        val chipGroup = binding.chipGroup
        val genreMap = discoverViewModel.genreMap.value
        val randomColor = discoverViewModel.randomColor.value
        chipGroup.removeAllViews()
        if (genreMap != null && randomColor!=null) {
            for ((key, value) in genreMap) {
                val genreChip = Chip(context)
                genreChip.id = key
                genreChip.isCheckable = true
                genreChip.text = value
                genreChip.setTextColor(Color.WHITE)
                genreChip.chipBackgroundColor = ColorStateList.valueOf(randomColor)
                chipGroup.addView(genreChip)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}