package com.example.cinemates.ui.details.tvShow


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentEpisodeGroupDetailsBinding
import com.example.cinemates.domain.model.tv.Group
import com.indisparte.home.adapter.SectionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private val TAG = EpisodeGroupDetailsFragment::class.simpleName

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
@AndroidEntryPoint
class EpisodeGroupDetailsFragment : BaseFragment<FragmentEpisodeGroupDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEpisodeGroupDetailsBinding
        get() = FragmentEpisodeGroupDetailsBinding::inflate

    private val tvDetailsViewModel: TvDetailsViewModel by activityViewModels()
    private lateinit var groupSectionAdapter: SectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        binding.recyclerView.adapter = groupSectionAdapter


        lifecycleScope.launchWhenStarted {

            tvDetailsViewModel.episodeGroupDetail.collectLatest { episodeGroup ->
                episodeGroup?.let {
                    // TODO: for each group of episode create a section with respective episodeDTOS
                    Log.d(TAG, "onViewCreated, episode group size ${episodeGroup.groups.size}")
                    binding.episodeGroup = it
                    for (group: Group in episodeGroup.groups) {
                    }

                }

            }
        }
    }

}