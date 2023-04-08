package com.example.cinemates.ui.adapter

import androidx.navigation.Navigation
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemEpisodeGroupBinding
import com.example.cinemates.databinding.ListItemEpisodeLongBinding
import com.example.cinemates.model.Episode
import com.example.cinemates.model.EpisodeGroup


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