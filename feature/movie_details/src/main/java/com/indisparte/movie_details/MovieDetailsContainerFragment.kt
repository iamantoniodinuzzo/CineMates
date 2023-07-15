package com.indisparte.movie_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.indisparte.ui.fragment.MediaDetailsContainerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
@AndroidEntryPoint
class MovieDetailsContainerFragment : MediaDetailsContainerFragment(
    linkedMapOf(
        MovieAboutFragment() to "About",
    )
) {
    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}