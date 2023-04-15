package com.example.cinemates.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentSearchBinding
import com.example.cinemates.ui.adapter.MediaAdapter
import com.example.cinemates.ui.adapter.PersonAdapter
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.util.ViewSize
import kotlinx.coroutines.launch


private const val SPAN_COLUMN = 3

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    private lateinit var selectedLayoutManager: LayoutManager
    private lateinit var mMediaAdapter: MediaAdapter
    private lateinit var personAdapter: PersonAdapter
    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init adapters
        mMediaAdapter = MediaAdapter()
        personAdapter = PersonAdapter()
        tvShowAdapter = TvShowAdapter()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Listen menu item click and change layout into recyclerview
            // owned by SearchActor & SearchMovie fragment
            toolbar.apply {
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_switch_grid -> viewModel.setIsGridLayout(true)
                        R.id.menu_switch_list -> viewModel.setIsGridLayout(false)
                    }
                    false
                }

                //close keyboard when back button pressed
                setNavigationOnClickListener { view ->
                    (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                        view.windowToken,
                        0
                    )
                    requireActivity().onBackPressed()
                }
            }

            //Set query parameter and open keyboard default
            searchView.apply {
                isIconified = false
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        //NOTE set this query into viewModel
                        viewModel.setQuery(query)
                        return false
                    }

                    override fun onQueryTextChange(query: String): Boolean {
                        return false
                    }
                })

            }


            //listen viewModel changes
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                launch {
                    viewModel.searchedMovies.collect { movies ->
                        mMediaAdapter.updateItems(movies)
                    }
                }
                launch {
                    viewModel.searchedActors.collect { actors ->
                        personAdapter.updateItems(actors)
                    }
                }

                launch {
                    viewModel.searchedTvShow.collect { tvShow ->
                        tvShowAdapter.updateItems(tvShow)
                    }
                }

                launch {
                    viewModel.isGridLayout.collect {
                        it?.let {
                            if (it) {
                                updateLayout(
                                    GridLayoutManager(requireContext(), SPAN_COLUMN),
                                    ViewSize.SMALL
                                )
                            } else {
                                updateLayout(LinearLayoutManager(requireContext()), ViewSize.LONG)
                            }
                            val gridView = toolbar.menu.findItem(R.id.menu_switch_grid)
                            gridView.isVisible = !it
                            val listView = toolbar.menu.findItem(R.id.menu_switch_list)
                            listView.isVisible = it

                            recyclerView.layoutManager = selectedLayoutManager

                        }
                    }
                }

            }
            recyclerView.adapter = mMediaAdapter


            // Set up the filter chips
            var lastCheckedId = -1
            chipGroup.setOnCheckedChangeListener { _, checkedId ->
                if (checkedId == lastCheckedId) {
                    // The same chip has been selected twice.
                    return@setOnCheckedChangeListener
                }
                lastCheckedId = checkedId
                val adapter = when (checkedId) {
                    R.id.movies_chip -> mMediaAdapter
                    R.id.actors_chip -> personAdapter
                    R.id.tv_show_chip -> tvShowAdapter
                    else -> throw IllegalArgumentException("Invalid filter chip ID")
                }
                recyclerView.adapter = adapter
            }
        }
    }

    private fun updateLayout(layoutManager: LayoutManager, viewSize: ViewSize) {
        Log.d(
            SearchFragment::class.simpleName,
            "updateLayoutType: Small View"
        )
        selectedLayoutManager = layoutManager
        tvShowAdapter.currentLayoutType = viewSize
        mMediaAdapter.currentLayoutType = viewSize
        personAdapter.currentLayoutType = viewSize
    }



}