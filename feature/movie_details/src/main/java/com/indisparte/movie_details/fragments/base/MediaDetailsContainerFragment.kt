package com.indisparte.movie_details.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.indisparte.movie_details.databinding.FragmentMediaDetailsContainerBinding
import com.indisparte.ui.adapter.ViewPagerAdapter
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible
import kotlin.math.abs

typealias FragmentTitleMap = LinkedHashMap<Fragment, @receiver:StringRes Int>

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
// TODO: Generalize this class for PersonDetailsContainer.
//  Need to pass View binding with generics and set a default view binding

abstract class MediaDetailsContainerFragment(
    private val mapOfFragments: FragmentTitleMap,
) : BaseFragment<FragmentMediaDetailsContainerBinding>() {
    /**
     * Secondary constructor used when no fragments are initially provided.
     */
    constructor() : this(linkedMapOf())

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMediaDetailsContainerBinding
        get() = FragmentMediaDetailsContainerBinding::inflate

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    protected abstract fun saveMedia()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewPager()

        binding.apply {
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            fab.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
                saveMedia()
            }


            // FIXME Hide and show FAB when scrolling, add animations
            appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                fab.isVisible =
                    verticalOffset == 0 || abs(verticalOffset) >= appBarLayout.totalScrollRange
            }

        }
    }


    private fun initializeViewPager() {
        binding.apply {
            viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)

            mapOfFragments.forEach { (fragment, titleRes) ->
                val title = requireContext().getString(titleRes)
                viewPagerAdapter.addFragment(fragment, title)
            }

            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = viewPagerAdapter.getPageTitle(position)
            }.attach()
        }
    }

    /**
     * Add a new fragment to the ViewPager.
     * @param fragment The fragment to add.
     * @param titleRes The string resource for the fragment's title.
     */
    protected fun addFragment(fragment: Fragment, titleRes: Int) {
        val title = requireContext().getString(titleRes)

        if (!mapOfFragments.containsKey(fragment)) {
            mapOfFragments[fragment] = titleRes
            viewPagerAdapter.addFragment(fragment, title)
            val index = viewPagerAdapter.getFragmentIndex(fragment)
            viewPagerAdapter.notifyItemInserted(index)
        }
    }


    /**
     * Remove a fragment from the ViewPager, if present
     * @param fragment The fragment to remove.
     */
    protected fun removeFragment(fragment: Fragment) {
        mapOfFragments.remove(fragment)
        val index = viewPagerAdapter.getFragmentIndex(fragment)
        viewPagerAdapter.removeFragment(fragment)
        viewPagerAdapter.notifyItemRemoved(index)

    }

    protected fun showLoading() {
        binding.loadingProgressBar.visible()
        binding.content.gone()
    }

    protected fun hideLoading() {
        binding.loadingProgressBar.gone()
        binding.content.visible()
    }

}
