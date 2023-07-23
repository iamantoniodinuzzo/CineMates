package com.indisparte.movie_details.fragments

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.github.ajalt.timberkt.Timber
import com.indisparte.model.entity.Movie
import com.indisparte.movie_details.fragments.base.ListFragment
import com.indisparte.network.Resource
import com.indisparte.network.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.util.extension.collectIn


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieSimilarFragment :
    ListFragment<Movie, ListItemMediaSmallBinding, MovieAdapter>(MovieAdapter()) {
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun initializeViews() {
        binding.recyclerView.apply {
            // Set the RecyclerView to use a linear layout by default
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    override fun addItemsToTheAdapter() {
        adapter.setFragment(this)//necessary to navigation
        viewModel.similarMovies.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { similar ->
                    Timber.tag("MovieSimilarFragment")
                        .d("Content loaded: ${similar?.map { it.mediaName }}")
                    adapter.submitList(similar)
                },
                onError = { error ->
                    Timber.tag("MovieSimilarFragment").e("Error: ${error?.message}")

                },
                onLoading = {
                    Timber.tag("MovieSimilarFragment").d("Loading content...")

                }
            )
        }
    }

}