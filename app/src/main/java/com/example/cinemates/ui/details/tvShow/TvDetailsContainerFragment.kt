package com.example.cinemates.ui.details.tvShow

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.cinemates.ui.details.MediaDetailsContainerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private val TAG = TvDetailsContainerFragment::class.simpleName

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
class TvDetailsContainerFragment : MediaDetailsContainerFragment(
    mapOf(
        TvAboutFragment() to "About",
        TvSeasonsFragment() to "Seasons",
        TvCastFragment() to "Cast",
        TvCrewFragment() to "Crew",
        TvCreatedByFragment() to "Creators",
        TvSimilarFragment() to "Similar"
    )
) {

    private val viewModel: TvDetailsViewModel by activityViewModels()
    private val args: TvDetailsContainerFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onDetailsFragmentReady(args.media.id)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            launch {
                viewModel.selectedTv.collectLatest { selectedTv ->
                    binding.media = selectedTv
                }
            }

            launch {
                viewModel.posters.collectLatest {
                    this@TvDetailsContainerFragment.posters = it
                }
            }
            launch {
                viewModel.backdrops.collectLatest {
                    this@TvDetailsContainerFragment.backdrops= it
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onFragmentDestroyed()
    }

}