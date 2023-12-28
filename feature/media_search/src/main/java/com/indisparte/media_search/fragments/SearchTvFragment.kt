package com.indisparte.media_search.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.util.whenResources
import com.indisparte.tv.TvShow
import com.indisparte.ui.adapter.TvShowAdapter
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn


/**
 * @author Antonio Di Nuzzo
 */
class SearchTvFragment :
    ListFragment<TvShow, ListItemMediaSmallBinding, TvShowAdapter>(TvShowAdapter()) {

    private val viewModel: SearchViewModel by viewModels({requireParentFragment()})
    private val TAG: String = SearchTvFragment::class.simpleName!!

    override fun initializeViews() {
        //init recyclerview
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            //Base empty state
            setEmptyStateTitle("Search CineMates")
            setEmptyStateSubtitle("Find your favorites movies, TV shows and people.")
            setEmptyStateImage(com.indisparte.network.R.drawable.ic_empty_box)
        }
        adapter.setOnItemClickListener { item ->
            val activity = requireActivity()
            if (activity is ToFlowNavigable) {
                // TODO: Navigate to TvShow details fragment
            }
        }
    }

    override fun addItemsToTheAdapter() {
        viewModel.tvShowBySearch.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = { movies ->
                    binding.recyclerView.hideLoading()
                    adapter.submitList(movies)
                },
                onError = { exception ->
                   showError(exception)
                },
                onLoading = {
                    binding.recyclerView.showLoading()
                }
            )
        }
    }

}