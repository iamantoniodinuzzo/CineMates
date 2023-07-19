package com.indisparte.movie_details.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.github.ajalt.timberkt.Timber
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.Resource
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 */
@AndroidEntryPoint
class MovieDetailsContainerFragment : MediaDetailsContainerFragment(
    linkedMapOf(
        MovieAboutFragment() to "About",
        MovieCastFragment() to "Cast"
    )
) {
    private val viewModel: MovieDetailsViewModel by activityViewModels()
    private val movieIdArgs: MovieDetailsContainerFragmentArgs by navArgs()
    override fun initializeViews() {
        viewModel.onDetailsFragmentReady(movieIdArgs.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            ((this.requireActivity()) as ToFlowNavigable).navigateToFlow(NavigationFlow.HomeFlow)
        }


        viewModel.selectedMovie.collectIn(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Error -> {
                    val error = resources.error
                    Timber.tag("MovieDetailsContainer").d("Error: $error")
                }

                is Resource.Loading -> {
                    Timber.tag("MovieDetailsContainer").d("Content loading...")
                }

                is Resource.Success -> {
                    val movieDetails = resources.data
                    Timber.tag("MovieDetailsContainer")
                        .d("Content loaded: ${movieDetails.toString()}")
                    binding.media = movieDetails
                }
            }
        }

    }


}