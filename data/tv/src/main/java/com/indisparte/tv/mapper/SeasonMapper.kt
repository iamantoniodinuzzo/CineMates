package com.indisparte.tv.mapper

import com.indisparte.tv.Season
import com.indisparte.tv.SeasonDetails
import com.indisparte.tv.response.SeasonDTO
import com.indisparte.tv.response.SeasonDetailsDTO


fun SeasonDTO.mapToSeason(): Season {
    return Season(
        airDate = this.airDate,
        episodeCount = this.episodeCount,
        id = this.id,
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath,
        seasonNumber = this.seasonNumber,
        voteAverage = this.voteAverage
    )
}

fun SeasonDetailsDTO.mapToSeasonDetails(): SeasonDetails {
    return SeasonDetails(
        airDate = this.airDate,
        episodes = this.episodes.map { it.mapToEpisode() },
        id = this.id,
        seasonId = this.seasonId,
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath,
        seasonNumber = this.seasonNumber,
        voteAverage = this.voteAverage
    )
}