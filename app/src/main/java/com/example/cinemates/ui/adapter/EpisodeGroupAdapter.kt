package com.example.cinemates.ui.adapter

import androidx.navigation.Navigation
import com.example.cinemates.R
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.databinding.ListItemEpisodeGroupBinding
import com.example.cinemates.domain.model.EpisodeGroup
import com.example.cinemates.ui.details.tvShow.TvDetailsContainerFragmentDirections


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class EpisodeGroupAdapter : SingleViewAdapter<EpisodeGroup, ListItemEpisodeGroupBinding>(
    R.layout.list_item_episode_group,
) {
    override fun onBindItem(binding: ListItemEpisodeGroupBinding, item: EpisodeGroup) {
        binding.episodeGroup = item
        binding.root.setOnClickListener {
            val action =
                TvDetailsContainerFragmentDirections.actionTvDetailsFragmentToEpisodeGroupDetailsFragment(
                    item
                )
            Navigation.findNavController(it).navigate(action)
        }
    }

}