package com.example.cinemates.ui.adapter

import com.example.cinemates.R
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.databinding.ListItemEpisodeLongBinding
import com.example.cinemates.domain.model.tv.Episode


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class EpisodeAdapter : SingleViewAdapter<Episode, ListItemEpisodeLongBinding>(
    R.layout.list_item_episode_long,
) {
    override fun onBindItem(binding: ListItemEpisodeLongBinding, item: Episode) {
        binding.episode = item
    }

}