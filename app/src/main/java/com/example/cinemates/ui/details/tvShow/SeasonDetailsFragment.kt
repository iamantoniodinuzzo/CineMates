package com.example.cinemates.ui.details.tvShow


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentSeasonDetailsBinding
import com.example.cinemates.domain.model.Group
import com.example.cinemates.domain.model.section.SectionEpisodesGroup
import com.example.cinemates.ui.adapter.EpisodeAdapter
import com.example.cinemates.ui.adapter.SectionAdapter
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
    private val args: SeasonDetailsFragmentArgs by navArgs()
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        episodeAdapter = EpisodeAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        binding.recyclerView.adapter = episodeAdapter


        lifecycleScope.launchWhenStarted {

            tvDetailsViewModel.getSeasonDetails(args.season.seasonNumber)
            tvDetailsViewModel.seasonDetails.collectLatest { seasonDetails ->
                seasonDetails?.let {
                    binding.season = it
                    episodeAdapter.items = it.seasonEpisodes

                }

            }
        }
    }

}