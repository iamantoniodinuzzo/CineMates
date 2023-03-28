package com.example.cinemates.ui.details.tvShow


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.cinemates.databinding.FragmentEpisodeGroupDetailsBinding
import com.example.cinemates.model.Group
import com.example.cinemates.ui.adapter.EpisodeAdapter
import kotlinx.coroutines.flow.collectLatest

private val TAG = EpisodeGroupDetailsFragment::class.simpleName

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
class EpisodeGroupDetailsFragment : Fragment() {
    private var _binding: FragmentEpisodeGroupDetailsBinding? = null
    private val binding: FragmentEpisodeGroupDetailsBinding
        get() = _binding!!
    private val tvDetailsViewModel: TvDetailsViewModel by activityViewModels()
    private val args: EpisodeGroupDetailsFragmentArgs by navArgs()
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        episodeAdapter = EpisodeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEpisodeGroupDetailsBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        binding.recyclerView.adapter = episodeAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {

            tvDetailsViewModel.getEpisodeGroupDetails(args.episodeGroupId)
            tvDetailsViewModel.episodeGroupDetail.collectLatest { episodeGroup ->
                episodeGroup?.let {
                    Log.d(TAG, "onViewCreated, episode group size ${episodeGroup.groups.size}")
                    binding.episodeGroup = it
                    for (group: Group in episodeGroup.groups) {
                        Log.d(TAG, "onViewCreated: $group")
                        episodeAdapter.updateItems(group.episodes)
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}