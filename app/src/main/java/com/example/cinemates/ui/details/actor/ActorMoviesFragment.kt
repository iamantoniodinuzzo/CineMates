package com.example.cinemates.ui.details.actor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.databinding.ListItemMediaSmallBinding
import com.example.cinemates.common.ListFragment
import com.example.cinemates.domain.model.Media
import com.example.cinemates.ui.adapter.MovieAdapter
import kotlinx.coroutines.launch

private val TAG = ActorMoviesFragment::class.simpleName
/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorMoviesFragment : ListFragment<Media, ListItemMediaSmallBinding, MovieAdapter>(
    MovieAdapter()
) {

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