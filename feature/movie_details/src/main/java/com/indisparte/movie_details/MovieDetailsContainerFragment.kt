package com.indisparte.movie_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
@AndroidEntryPoint
internal class MovieDetailsContainerFragment : MediaDetailsContainerFragment(
    linkedMapOf(
        MovieAboutFragment() to "About",
    )
) {
    private val viewModel: MovieDetailsViewModel by activityViewModels()
    private val movieIdArgs: MovieDetailsContainerFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MovieDetailsContainer", "Movie id: ${movieIdArgs.id}")


    }

}