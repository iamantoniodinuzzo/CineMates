package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.SeasonDetailsDTO
import com.example.cinemates.domain.model.SeasonDetails


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun SeasonDetailsDTO.mapToSeasonDetails():SeasonDetails{
    return SeasonDetails(
        airDate = this.formattedAirDate,
        seasonEpisodes = this.seasonEpisodes.map { it.mapToSeasonEpisode() },
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath?:"",
        seasonNumber = this.seasonNumber
    )
}