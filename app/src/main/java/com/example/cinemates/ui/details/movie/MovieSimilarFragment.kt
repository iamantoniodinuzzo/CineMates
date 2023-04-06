package com.example.cinemates.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.databinding.ListItemMediaSmallBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.ui.ListFragment
import com.example.cinemates.ui.adapter.MovieAdapter

class MovieSimilarFragment : ListFragment<Movie, ListItemMediaSmallBinding, MovieAdapter>(
    MovieAdapter()
) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.similarMovies.collect { similar ->
                adapter.updateItems(similar)
                binding.counter = similar.size
            }
        }

    }

}