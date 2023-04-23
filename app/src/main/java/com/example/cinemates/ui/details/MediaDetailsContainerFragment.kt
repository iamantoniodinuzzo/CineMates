package com.example.cinemates.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentMediaDetailsBinding
import com.example.cinemates.domain.model.Image
import com.example.cinemates.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

private val TAG = MediaDetailsContainerFragment::class.simpleName
/**
 * A base class for implementing a container of other fragments shown via viewPager and tab layout
 *
 * @param mapOfFragments Map of fragments to display in viewPager.
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
abstract class MediaDetailsContainerFragment(private val mapOfFragments: Map<Fragment, String>) :
    BaseFragment<FragmentMediaDetailsBinding>() {

    constructor() : this(mapOf())

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMediaDetailsBinding
        get() = FragmentMediaDetailsBinding::inflate

    protected lateinit var viewPagerAdapter: ViewPagerAdapter
    protected var posters: List<Image>? = null
    protected var backdrops: List<Image>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated called")
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

            mediaPoster.root.setOnClickListener {
                posters?.let {
                    displayImages(it.toTypedArray())
                }
            }

            mediaBackdrop.setOnClickListener {
                backdrops?.let {
                    displayImages(it.toTypedArray())
                }
            }



            initializeViewPager()
        }
    }

    private fun displayImages(image: Array<Image>) {
        val action = NavGraphDirections.actionGlobalMediaImagesFragment(image)
        Navigation.findNavController(requireView()).navigate(action)
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
                tab.text = mapOfFragments.values.toList()[position]
            }.attach()
        }

    }


}