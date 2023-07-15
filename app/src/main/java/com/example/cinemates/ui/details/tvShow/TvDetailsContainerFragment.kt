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
    )
) {

    private val viewModel: TvDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            launch {
                viewModel.selectedTv.collectLatest { selectedTv ->
                    binding.media = selectedTv
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onFragmentDestroyed()
    }

}