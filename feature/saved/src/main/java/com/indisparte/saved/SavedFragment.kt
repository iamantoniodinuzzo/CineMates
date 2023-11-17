package com.indisparte.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.indisparte.saved.databinding.FragmentSavedBinding
import com.indisparte.saved.history.HistoryFragment
import com.indisparte.saved.watchlist.WatchlistFragment
import com.indisparte.ui.adapter.ViewPagerAdapter
import com.indisparte.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
private typealias SavedFragmentTitleMap = LinkedHashMap<Fragment, @receiver:StringRes Int>

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSavedBinding
        get() = FragmentSavedBinding::inflate
    private val LOG = Timber.tag(SavedFragment::class.java.simpleName)
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var savedFragments: SavedFragmentTitleMap

    override fun initializeViews() {
        //init views here
        savedFragments = linkedMapOf(
            WatchlistFragment() to R.string.saved_fragment_watchlist_title,
            HistoryFragment() to R.string.saved_fragment_history_title
        )

        //init view pager
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.apply {

            viewPager.adapter = viewPagerAdapter
            savedFragments.forEach { (fragment, titleRes) ->
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


    }

}