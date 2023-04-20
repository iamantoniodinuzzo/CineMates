package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupDetailsDTO
import com.example.cinemates.domain.model.EpisodeGroupDetails


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun EpisodeGroupDetailsDTO.mapToEpisodeGroupDetails():EpisodeGroupDetails{
    return EpisodeGroupDetails(
        description = this.description,
        episodeCount = this.episodeCount,
        groupCount = this.groupCount,
        id = this.id,
        name = this.name,
        network = this.network.mapToNetwork(),
        type = this.episodeGroupType,
        groups = this.groups.map {
            it.mapToGroup()
        }
    )
}