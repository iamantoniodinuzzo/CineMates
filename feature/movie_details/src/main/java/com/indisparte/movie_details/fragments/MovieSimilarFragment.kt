package com.indisparte.movie_details.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.github.ajalt.timberkt.Timber
import com.indisparte.model.entity.Movie
import com.indisparte.movie_details.fragments.base.ListFragment
import com.indisparte.network.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.adapter.OnItemClickListener
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.util.extension.collectIn


/**
 * @author Antonio Di Nuzzo
 */
class MovieSimilarFragment :
    ListFragment<Movie, ListItemMediaSmallBinding, MovieAdapter>(MovieAdapter()) {
    private val viewModel: MovieDetailsViewModel by viewModels({ requireParentFragment() })
    private val LOG = Timber.tag("MovieSimilarFragment")
    override fun initializeViews() {
        binding.recyclerView.apply {
            // Set the RecyclerView to use a linear layout by default
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    override fun addItemsToTheAdapter() {
        adapter.setOnItemClickListener(object : OnItemClickListener<Movie> {
            override fun onItemClick(item: Movie) {
                //update view model selected movie
                viewModel.onDetailsFragmentReady(item.id)
            }

        })
        viewModel.similarMovies.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { similar ->
                    LOG.d("Content loaded: ${similar.map { it.mediaName }}")
                    hideLoading()
                    adapter.submitList(similar)
                },
                onError = { error ->
                    val errorMessage = error?.message
                    LOG.e("Error: $errorMessage")
                    binding.recyclerView.setEmptyStateSubtitle(errorMessage)
                    hideLoading()

                },
                onLoading = {
                    LOG.d("Loading content...")
                    showLoading()

                }
            )
        }
    }

}