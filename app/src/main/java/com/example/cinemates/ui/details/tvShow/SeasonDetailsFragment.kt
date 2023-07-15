package com.example.cinemates.ui.details.tvShow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentSeasonDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private val TAG = EpisodeGroupDetailsFragment::class.simpleName

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
@AndroidEntryPoint
class SeasonDetailsFragment : BaseFragment<FragmentSeasonDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeasonDetailsBinding
        get() = FragmentSeasonDetailsBinding::inflate

    private val tvDetailsViewModel: TvDetailsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}