package com.example.cinemates.ui.details.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.cinemates.ui.details.MediaDetailsContainerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private val TAG = MovieDetailsContainerFragment::class.simpleName

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
@AndroidEntryPoint
class MovieDetailsContainerFragment : MediaDetailsContainerFragment(
    mapOf(
        MovieAboutFragment() to "About",
    )
) {
    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated called")

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            launch {
                viewModel.selectedMovie.collect { selectedMovie ->
                    binding.media = selectedMovie
                }
            }


        }


    }

}