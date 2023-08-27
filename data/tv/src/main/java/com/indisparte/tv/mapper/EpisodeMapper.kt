package com.indisparte.tv.mapper

import com.indisparte.model.entity.tv.CreatedBy
import com.indisparte.model.entity.tv.Episode
import com.indisparte.model.entity.tv.EpisodeGroup
import com.indisparte.model.entity.tv.EpisodeGroupDetails
import com.indisparte.tv.response.CreatedByDTO
import com.indisparte.tv.response.EpisodeDTO
import com.indisparte.tv.response.EpisodeGroupDTO
import com.indisparte.tv.response.EpisodeGroupDetailsDTO


fun EpisodeGroupDTO.mapToEpisodeGroup(): EpisodeGroup {
    return EpisodeGroup(
        description = this.description,
        episodeCount = this.episodeCount,
        groupCount = this.groupCount,
        id = this.id,
        name = this.name,
        network = this.network,
        type = this.type
    )
}

fun EpisodeGroupDetailsDTO.mapToEpisodeGroupDetails(): EpisodeGroupDetails {
    return EpisodeGroupDetails(
        description = this.description,
        episodeCount = this.episodeCount,
        groupCount = this.groupCount,
        groups = this.groups.map { it.mapToGroup() },
        id = this.id,
        name = this.name,
        network = this.network?.mapToNetwork(),
        type = this.type
    )
}

fun EpisodeDTO.mapToEpisode(): Episode {
    return Episode(
        airDate = this.airDate,
        episodeNumber = this.episodeNumber,
        id = this.id,
        name = this.name,
        overview = this.overview,
        runtime = this.runtime,
        seasonNumber = this.seasonNumber,
        showId = this.showId,
        stillPath = this.stillPath,
        voteAverage = this.voteAverage,
    )
}

fun CreatedByDTO.mapToCreatedBy(): CreatedBy {
    return CreatedBy(
        creditId = this.creditId,
        gender = this.gender,
        id = this.id,
        name = this.name,
        profilePath = this.profilePath
    )
}