package com.indisparte.tv.mapper

import com.indisparte.tv.CreatedBy
import com.indisparte.tv.Episode
import com.indisparte.tv.EpisodeGroup
import com.indisparte.tv.EpisodeGroupDetails
import com.indisparte.tv.response.CreatedByDTO
import com.indisparte.tv.response.EpisodeDTO
import com.indisparte.tv.response.EpisodeGroupDTO
import com.indisparte.tv.response.EpisodeGroupDetailsDTO


fun EpisodeGroupDTO.mapToEpisodeGroup(): com.indisparte.tv.EpisodeGroup {
    return com.indisparte.tv.EpisodeGroup(
        description = this.description,
        episodeCount = this.episodeCount,
        groupCount = this.groupCount,
        id = this.id,
        name = this.name,
        network = this.network,
        type = this.type
    )
}

fun EpisodeGroupDetailsDTO.mapToEpisodeGroupDetails(): com.indisparte.tv.EpisodeGroupDetails {
    return com.indisparte.tv.EpisodeGroupDetails(
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

fun EpisodeDTO.mapToEpisode(): com.indisparte.tv.Episode {
    return com.indisparte.tv.Episode(
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

fun CreatedByDTO.mapToCreatedBy(): com.indisparte.tv.CreatedBy {
    return com.indisparte.tv.CreatedBy(
        creditId = this.creditId,
        gender = this.gender,
        id = this.id,
        name = this.name,
        profilePath = this.profilePath
    )
}