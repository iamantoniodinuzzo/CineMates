package com.example.cinemates.ui.adapter

import com.example.cinemates.R
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.databinding.ListItemEpisodeGroupBinding


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class EpisodeGroupAdapter : SingleViewAdapter<EpisodeGroup, ListItemEpisodeGroupBinding>(
    R.layout.list_item_episode_group,
) {
    override fun onBindItem(binding: ListItemEpisodeGroupBinding, item: EpisodeGroup) {
        binding.episodeGroup = item
        binding.root.setOnClickListener {
           /* val action =
                TvDetailsFragmentDirections.actionTvDetailsFragmentToEpisodeGroupDetailsFragment(
                    item.id
                )
               Navigation.findNavController(it).navigate(action)*/
        }
    }

}