package com.example.cinemates.ui.details.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.cinemates.ui.details.MediaDetailsContainerFragment
import kotlinx.coroutines.launch

private val TAG = MovieDetailsContainerFragment::class.simpleName

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
class MovieDetailsContainerFragment : MediaDetailsContainerFragment(
    mapOf(
        MovieAboutFragment() to "About",
        MovieCastFragment() to "Cast",
        MovieCrewFragment() to "Crew",
        MovieSimilarFragment() to "Similar"
    )
) {
    private val args: MovieDetailsContainerFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onDetailsFragmentReady(args.media.id)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            launch {
                viewModel.selectedMovie.collect { selectedMovie ->
                    Log.d("TAG", "onViewCreated: ${selectedMovie.toString()}")
                    binding.media = selectedMovie
                }
            }
        }


    }

}