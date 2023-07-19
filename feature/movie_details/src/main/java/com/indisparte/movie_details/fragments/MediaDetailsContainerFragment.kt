package com.indisparte.movie_details.fragments

import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.indisparte.movie_details.databinding.FragmentMediaDetailsBinding
import com.indisparte.ui.adapter.ViewPagerAdapter
import com.indisparte.ui.fragment.BaseFragment
import kotlin.math.abs

/**
 * Abstract base class for a media details container fragment.
 *
 * This class provides a container for displaying media details, such as images, in a ViewPager
 * with associated tabs. It also handles the initialization of the ViewPager and its associated
 * fragments.
 *
 * @param mapOfFragments The ordered map of fragments and their titles to be displayed in the ViewPager.
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 */
abstract class MediaDetailsContainerFragment(private val mapOfFragments: LinkedHashMap<Fragment, String>) :
    BaseFragment<FragmentMediaDetailsBinding>() {

    /**
     * Secondary constructor used when no fragments are initially provided.
     */
    constructor() : this(linkedMapOf())

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMediaDetailsBinding
        get() = FragmentMediaDetailsBinding::inflate

    protected lateinit var viewPagerAdapter: ViewPagerAdapter
    protected var posters: List<Image>? = null
    protected var backdrops: List<Image>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewPager()

        binding.apply {
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            fab.setOnClickListener {
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }
            watchProviders.setOnClickListener {
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }

            // Hide and show FAB when scrolling
            appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                fab.isVisible = verticalOffset == 0 || abs(verticalOffset) >= appBarLayout.totalScrollRange
            }

        }
    }

    /**
     * Displays the images in the media details.
     *
     * @param images The media images to be displayed.
     */
    private fun displayImages(images: MediaStore.Images) {
        // todo Implement logic to displaying images
    }

    private fun initializeViewPager() {
        binding.apply {
            viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)

            mapOfFragments.forEach { (fragment, title) ->
                viewPagerAdapter.addFragment(fragment, title)
            }

            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = mapOfFragments.values.toList()[position]
            }.attach()
        }
    }
}
