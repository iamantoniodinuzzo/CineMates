package com.indisparte.movie_details.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.indisparte.movie_data.Movie
import com.indisparte.network.util.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn
import timber.log.Timber


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
        adapter.setOnItemClickListener { item -> //update view model selected movie
            viewModel.onDetailsFragmentReady(item.id)
        }
        viewModel.similarMovies.collectIn(viewLifecycleOwner) { resources ->
            resources.whenResources(
                onSuccess = { similar ->
                    hideLoading()
                    adapter.submitList(similar)
                },
                onError = { error ->
                    showError(error)
                },
                onLoading = {
                    showLoading()

                }
            )
        }
    }

}