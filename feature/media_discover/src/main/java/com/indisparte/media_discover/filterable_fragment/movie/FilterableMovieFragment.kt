package com.indisparte.media_discover.filterable_fragment.movie

import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.indisparte.media_discover.R
import com.indisparte.media_discover.filterable_fragment.FilterSheetRequestListener
import com.indisparte.movie_data.Movie
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.exception.CineMatesException
import com.indisparte.network.util.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.custom_view.showError
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


/**
 * @author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class FilterableMovieFragment :
    ListFragment<Movie, ListItemMediaSmallBinding, MovieAdapter>(
        MovieAdapter()
    ), FilterSheetRequestListener {

    private val viewModel: FilterableMovieFragmentViewModel by navGraphViewModels(R.id.discover_graph) {
        defaultViewModelProviderFactory
    }

    override fun initializeViews() {
        //init recyclerview
        binding.lifecycleOwner = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            showError(CineMatesException.EmptyResponse)
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
                    hideLoading()
                    adapter.submitList(movies)
                },
                onError = { exception ->
                    showError(exception)
                },
                onLoading = {
                    showLoading()
                }
            )

        }


    }

    override fun onRequestFilterSheet() {
        try {
            findNavController().navigate(R.id.action_customFilterFragment_to_movieFilter)
        } catch (e: IllegalArgumentException) {
            Timber.tag("FilterableMovieFragment")
                .e("App crash denied, clicked two times on filter menu item")
        }
    }

}