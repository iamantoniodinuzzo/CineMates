package com.indisparte.media_search.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.indisparte.media_search.databinding.FragmentSearchBinding
import com.indisparte.ui.adapter.ViewPagerAdapter
import com.indisparte.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

private typealias SearchFragmentTitleMap = LinkedHashMap<Fragment, @receiver:StringRes Int>

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var searchFragments: SearchFragmentTitleMap
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun initializeViews() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        searchFragments = linkedMapOf(
            SearchMovieFragment() to com.indisparte.ui.R.string.fragment_movie_title
        )

        binding.apply {

            toolbar.setNavigationOnClickListener { view ->
                //close keyboard when back button pressed
                (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    view.windowToken,
                    0
                )

                findNavController().navigateUp()
            }

            //Set query parameter and open keyboard default
            searchView.apply {
                isIconified = false

                setOnCloseListener {
                    //when user clean input, clean results
                    viewModel.updateQuery("")
                    true
                }
                
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(query: String): Boolean {
                        //NOTE set this query into viewModel
                        viewModel.updateQuery(query)
                        return true
                    }
                })
            }

            //Init view pager
            viewPager.adapter = viewPagerAdapter
            searchFragments.forEach { (fragment, titleRes) ->
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
    }




}