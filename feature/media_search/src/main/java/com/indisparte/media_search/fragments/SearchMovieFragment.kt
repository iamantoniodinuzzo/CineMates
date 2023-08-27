package com.indisparte.media_search.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.github.ajalt.timberkt.Timber
import com.indisparte.media_search.R
import com.indisparte.model.entity.movie.Movie
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.adapter.OnItemClickListener
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn


/**
 * @author Antonio Di Nuzzo
 */
class SearchMovieFragment :
    ListFragment<Movie, ListItemMediaSmallBinding, MovieAdapter>(MovieAdapter()) {

    private val viewModel: SearchViewModel by viewModels({requireParentFragment()})
    private val TAG: String = SearchMovieFragment::class.simpleName!!

    override fun initializeViews() {
        //init recyclerview
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            setEmptyStateTitle("Search CineMates")
            setEmptyStateSubtitle("Find your favorites movies, TV shows and people.")
            setEmptyStateImage(R.drawable.ic_search)
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
        viewModel.moviesBySearch.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = { movies ->
                    Timber.tag(TAG).d("Search result: $movies")
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
                    Timber.tag(TAG).d("Loading search results")
                    binding.recyclerView.showLoading()
                }
            )
        }
    }

}