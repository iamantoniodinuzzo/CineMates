package com.example.cinemates.view.ui.discover

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.Navigation.findNavController
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentDiscoverBinding
import com.example.cinemates.model.data.Filter
import com.example.cinemates.util.getLong
import com.google.android.material.chip.Chip
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.material.transition.platform.MaterialElevationScale
import java.util.*

class DiscoverFragment : Fragment() {
    private var _binding: FragmentDiscoverBinding? = null
    private val binding: FragmentDiscoverBinding
        get() = _binding!!
    private val mRnd = Random()
    private val discoverViewModel: DiscoverViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupMotionAnimations()
    }

    private fun setupMotionAnimations() {
        enterTransition = MaterialFadeThrough()
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getLong(R.integer.material_motion_duration_long_2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateChipGroup()
        val filterBuilder = Filter.Builder()

        binding.apply {

            searchButton.setOnClickListener { view ->
                findNavController(view).navigate(
                    R.id.action_discoverFragment_to_searchFragment
                )
            }

            //Genres
            chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->

                val elevationScaleTransition = MaterialElevationScale(true).apply {
                    interpolator = FastOutSlowInInterpolator()
                }
                TransitionManager.beginDelayedTransition(
                    group.rootView as ViewGroup,
                    elevationScaleTransition
                )

                if (checkedIds.isNotEmpty()) {
                    buttonGroup.visibility = View.VISIBLE
                    //discoverViewModel.updateSelectedGenres(checkedIds.toString())
                    filterBuilder.withGenres(checkedIds)
                } else {
                    buttonGroup.visibility = View.GONE
                }
            }

            //Sort By
            popularity.setOnCheckedChangeListener { _, isChecked->
                if(isChecked) {
                    filterBuilder.sortBy(Filter.Sort.POPULARITY)
                    buttonGroup.visibility = View.VISIBLE
                }else {
                    filterBuilder.sortBy(null)
                    buttonGroup.visibility = View.GONE
                }
            }
            releaseDate.setOnCheckedChangeListener { _, isChecked->
                if(isChecked)
                    filterBuilder.sortBy(Filter.Sort.RELEASE_DATE)
                else
                    filterBuilder.sortBy(null)
            }
            revenue.setOnCheckedChangeListener { _, isChecked->
                if(isChecked)
                    filterBuilder.sortBy(Filter.Sort.REVENUE)
                else
                    filterBuilder.sortBy(null)
            }
            voteAverage.setOnCheckedChangeListener { _, isChecked->
                if(isChecked)
                    filterBuilder.sortBy(Filter.Sort.VOTE_AVERAGE)
                else
                    filterBuilder.sortBy(null)
            }

            //Apply
            applyFilterBtn.setOnClickListener {
                val filter = filterBuilder.build()
                val action =
                    DiscoverFragmentDirections.actionDiscoverFragmentToFilterFragment(filter)
                findNavController(view).navigate(action)
            }

            //Clean
            cleanFilters.setOnClickListener {
                chipGroup.clearCheck()
                discoverViewModel.initGenres()
            }
        }

    }

    private fun populateChipGroup() {
        val chipGroup = binding.chipGroup
        val genreMap = discoverViewModel.genreMap.value
        chipGroup.removeAllViews()
        if (genreMap != null) {
            for ((key, value) in genreMap) {
                val genreChip = Chip(context)
                genreChip.id = key
                genreChip.isCheckable = true
                genreChip.text = value
                genreChip.setTextColor(Color.WHITE)
                genreChip.chipBackgroundColor = ColorStateList.valueOf(getRandomColor())
                chipGroup.addView(genreChip)
            }
        }
    }

    fun getRandomColor(): Int {
        val baseColor = R.color.vermilion_100 //TODO maybe this color can be customizable
        val baseRed = Color.red(baseColor)
        val baseGreen = Color.green(baseColor)
        val baseBlue = Color.blue(baseColor)
        val red = (baseRed + mRnd.nextInt(256)) / 2
        val green = (baseGreen + mRnd.nextInt(256)) / 2
        val blue = (baseBlue + mRnd.nextInt(256)) / 2
        return Color.rgb(red, green, blue)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}