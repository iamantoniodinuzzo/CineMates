package com.indisparte.media_discover.custom_filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.indisparte.media_discover.R
import com.indisparte.media_discover.databinding.FragmentCustomFilterBinding
import com.indisparte.ui.adapter.ViewPagerAdapter
import com.indisparte.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

private typealias FilterableFragmentTitleMap = LinkedHashMap<Fragment, @receiver:StringRes Int>

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class FilterableContainerFragment : BaseFragment<FragmentCustomFilterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCustomFilterBinding
        get() = FragmentCustomFilterBinding::inflate
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var filterableFragments: FilterableFragmentTitleMap
    private val viewModel: FilterableFragmentViewModel by viewModels()
    private val TAG = FilterableContainerFragment::class.simpleName

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
                        findNavController().navigate(R.id.action_customFilterFragment_to_bottomSheetFilter)
                        true
                    }

                    else -> {
                        false
                    }
                }

            }
        }
    }

}