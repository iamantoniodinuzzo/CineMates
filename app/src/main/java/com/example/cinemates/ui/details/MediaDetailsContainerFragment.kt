package com.example.cinemates.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentMediaDetailsBinding
import com.example.cinemates.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

/**
 * A base class for implementing a container of other fragments shown via viewPager and tab layout
 *
 * @param mapOfFragments Map of fragments to display in viewPager.
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
@AndroidEntryPoint
open class MediaDetailsContainerFragment(private val mapOfFragments: Map<Fragment, String>) :
    Fragment() {

    constructor() : this(mapOf())

    private var _binding: FragmentMediaDetailsBinding? = null
    protected val binding: FragmentMediaDetailsBinding
        get() = _binding!!


    protected lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMediaDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            fab.setOnClickListener {
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }
            watchProviders.setOnClickListener { _ ->
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }

            //Hide and show FAB when scrolling
            appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (verticalOffset == 0) {
                    // Fully expanded state
                    fab.show()
                } else if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                    // Fully collapsed state
                    fab.hide()
                } else {
                    // Somewhere in between
                    fab.show()
                }
            }

            initializeViewPager()
        }


    }

    private fun initializeViewPager() {
        binding.apply {
            viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)

            mapOfFragments.map { (fragment, _) ->
                viewPagerAdapter.addFragment(fragment)
            }
            viewPager.adapter = viewPagerAdapter
            //viewPager.isUserInputEnabled = false
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = mapOfFragments.values.toList()[position].toString()
            }.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}