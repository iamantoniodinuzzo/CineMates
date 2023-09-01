package com.indisparte.media_discover.filterable_fragment.movie

import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.indisparte.media_discover.R
import com.indisparte.media_discover.filterable_fragment.FilterSheetRequestListener
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.error.CineMatesExceptions
import com.indisparte.network.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.custom_view.showError
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class FilterableMovieFragment :
    ListFragment<com.indisparte.movie_data.Movie, ListItemMediaSmallBinding, MovieAdapter>(
        MovieAdapter()
    ), FilterSheetRequestListener {

    private val TAG: String = FilterableMovieFragment::class.simpleName!!
    private val viewModel: FilterableMovieFragmentViewModel by navGraphViewModels(R.id.discover_graph) {
        defaultViewModelProviderFactory
    }

    override fun initializeViews() {
        //init recyclerview
        binding.lifecycleOwner = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            showError(CineMatesExceptions.EmptyResponse)
        }
        adapter.setOnItemClickListener { item ->
            val activity = requireActivity()
            if (activity is ToFlowNavigable) {
                activity.navigateToFlow(NavigationFlow.MovieDetailsFlow(item.id))
            }
        }
    }

    override fun addItemsToTheAdapter() {
        viewModel.filteredFilms.collectIn(viewLifecycleOwner) { result ->
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

    override fun onRequestFilterSheet() {
        findNavController().navigate(R.id.action_customFilterFragment_to_movieFilter)
    }

}