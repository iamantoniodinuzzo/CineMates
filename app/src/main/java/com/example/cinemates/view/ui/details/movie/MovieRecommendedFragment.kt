package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.model.Movie
import com.example.cinemates.view.ui.ListFragment
import kotlinx.coroutines.flow.collectLatest

class MovieRecommendedFragment : ListFragment<Movie, MovieAdapter>(MovieAdapter()) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.recommendedMovies.collectLatest { recommended ->
                adapter.addItems(recommended)
                binding.counter = recommended.size
            }
        }

    }

}