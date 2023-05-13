package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.SeasonEpisodeDTO
import com.example.cinemates.domain.mapper.credits.mapToGuestStar
import com.example.cinemates.domain.model.tv.SeasonEpisode


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */

fun SeasonEpisodeDTO.mapToSeasonEpisode(): SeasonEpisode {
    return SeasonEpisode(
        airDate = this.formattedAirDate,
        crew= this.crew,
        episodeNumber = this.episodeNumber,
        guestStar = this.guestStar.map { it.mapToGuestStar() },
        id = this.id,
        name = this.name,
        overview = this.overview,
        seasonNumber = this.seasonNumber,
        stillPath = this.stillPath?:"",
        voteAverage = this.voteAverage
    )
}