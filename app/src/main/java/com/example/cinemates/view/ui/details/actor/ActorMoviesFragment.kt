package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.MovieAdapter
import com.example.cinemates.databinding.FragmentListingItemsBinding
import com.example.cinemates.model.entities.Movie
import com.example.cinemates.view.ui.ListFragment

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorMoviesFragment : ListFragment<Movie, MovieAdapter>(MovieAdapter()) {

    private val viewModel: ActorDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movies.observe(viewLifecycleOwner) { moviesByActor ->
            adapter.addItems(moviesByActor)
            binding.counter = moviesByActor.size
        }

    }

}