package com.example.cinemates.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.databinding.ListItemMediaSmallBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.common.ListFragment
import com.example.cinemates.ui.adapter.MovieAdapter
import kotlinx.coroutines.flow.collectLatest

class MovieRecommendedFragment : ListFragment<Movie, ListItemMediaSmallBinding, MovieAdapter>(
    MovieAdapter()
) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.recommendedMovies.collectLatest { recommended ->
                adapter.updateItems(recommended)
            }
        }

    }

}