package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.EpisodeDTO
import com.example.cinemates.domain.model.tv.Episode


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */

fun EpisodeDTO.mapToEpisode(): Episode {
    return Episode(
        airDate = this.formattedAirDate,
        episodeNumber = this.episodeNumber,
        id = this.id,
        name = this.name,
        overview = this.overview,
        seasonNumber = this.seasonNumber,
        stillPath = this.stillPath?:"",
        voteAverage = this.voteAverage
    )
}