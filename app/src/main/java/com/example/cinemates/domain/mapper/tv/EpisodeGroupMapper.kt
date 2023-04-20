package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupDTO
import com.example.cinemates.domain.model.EpisodeGroup


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun EpisodeGroupDTO.mapToEpisodeGroup(): EpisodeGroup {
    return EpisodeGroup(
        description = this.description,
        episodeCount = this.episodeCount,
        groupCount = this.groupCount,
        id = this.id,
        name = this.name,
        network = this.network.mapToNetwork(),
        type = this.episodeGroupType
    )
}
