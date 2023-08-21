package com.indisparte.media_discover.custom_filters

import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.github.ajalt.timberkt.Timber
import com.indisparte.discover.util.MediaDiscoverFilter
import com.indisparte.discover.util.SortOptions
import com.indisparte.media_discover.R
import com.indisparte.model.entity.Movie
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.adapter.OnItemClickListener
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


/**
 * @author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class FilterableMovieFragment :
    ListFragment<Movie, ListItemMediaSmallBinding, MovieAdapter>(MovieAdapter()) {

    private val TAG: String = FilterableMovieFragment::class.simpleName!!
    private val viewModel: FilterableFragmentViewModel by navGraphViewModels(R.id.discover_graph){
        defaultViewModelProviderFactory
    }

    override fun initializeViews() {
        //init recyclerview
        binding.lifecycleOwner = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            setEmptyStateTitle("No results")
            setEmptyStateSubtitle("It seems that these filters are not matched by any film")
            setEmptyStateImage(R.drawable.ic_filters)
        }
        adapter.setOnItemClickListener(object : OnItemClickListener<Movie> {
            override fun onItemClick(item: Movie) {
                val activity = requireActivity()
                if (activity is ToFlowNavigable) {
                    activity.navigateToFlow(NavigationFlow.MovieDetailsFlow(item.id))
                }

            }

        })
    }

    override fun addItemsToTheAdapter() {
        viewModel.filteredFilms.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = { movies ->
                    Timber.tag(TAG).d("Filtering result: $movies")
                    binding.recyclerView.hideLoading()
                    adapter.submitList(movies)
                },
                onError = { exception ->
                    val errorMessage = exception?.message
                    Timber.tag(TAG).e("Error: $errorMessage")
                    binding.recyclerView.hideLoading()
                    binding.recyclerView.setEmptyStateSubtitle(errorMessage)
                },
                onLoading = {
                    Timber.tag(TAG).d("Loading the filter result")
                    binding.recyclerView.showLoading()
                }
            )

        }


    }

}