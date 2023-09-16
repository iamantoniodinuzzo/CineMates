package com.indisparte.media_discover.filterable_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.indisparte.media_discover.R
import com.indisparte.media_discover.databinding.FragmentContainerFilterableBinding
import com.indisparte.media_discover.filterable_fragment.movie.FilterableMovieFragment
import com.indisparte.ui.adapter.ViewPagerAdapter
import com.indisparte.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

private typealias FilterableFragmentTitleMap = LinkedHashMap<Fragment, @receiver:StringRes Int>

fun interface FilterSheetRequestListener {
    fun onRequestFilterSheet()
}

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class FilterableContainerFragment : BaseFragment<FragmentContainerFilterableBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContainerFilterableBinding
        get() = FragmentContainerFilterableBinding::inflate
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var filterableFragments: FilterableFragmentTitleMap
    private val TAG = FilterableContainerFragment::class.simpleName
    private var filterSheetRequestListener: FilterSheetRequestListener? = null
    override fun initializeViews() {
        filterableFragments = linkedMapOf(
            FilterableMovieFragment() to com.indisparte.ui.R.string.fragment_movie_title
        )

        //init view pager
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.apply {

            viewPager.adapter = viewPagerAdapter
            filterableFragments.forEach { (fragment, titleRes) ->
                val title = requireContext().getString(titleRes)
                viewPagerAdapter.addFragment(fragment, title)
            }
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = viewPagerAdapter.getPageTitle(position)
            }.attach()
        }
    }

    private fun performActionOnFragments() {
        for (i in 0 until viewPagerAdapter.itemCount) {
            val fragment = viewPagerAdapter.createFragment(i) as? FilterableMovieFragment
            fragment?.onRequestFilterSheet()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //use views here
        binding.apply {

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.filters -> {
                        performActionOnFragments()
                        true
                    }

                    else -> {
                        true
                    }
                }

            }
        }
    }

}