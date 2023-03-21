package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.view.ui.ListFragment
import com.example.cinemates.view.ui.adapter.MovieAdapter
import kotlinx.coroutines.launch

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorMoviesFragment : ListFragment<Movie,ListItemMovieSmallBinding,MovieAdapter>(MovieAdapter()) {

    private val viewModel: ActorDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movies.collect { moviesByActor ->
                adapter.updateItems(moviesByActor)
                binding.counter = moviesByActor.size
            }
        }


    }

}