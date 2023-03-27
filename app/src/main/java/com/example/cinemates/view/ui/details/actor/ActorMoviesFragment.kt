package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.view.ui.ListFragment
import com.example.cinemates.view.ui.adapter.MovieAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private val TAG = ActorMoviesFragment::class.simpleName
/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorMoviesFragment : ListFragment<Movie,ListItemMovieSmallBinding,MovieAdapter>(MovieAdapter()) {

    private val viewModel: ActorDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            launch {
                viewModel.movies.collect { moviesByActor ->
                    adapter.updateItems(moviesByActor)
                    binding.counter = moviesByActor.size
                }
            }

        }


    }

}