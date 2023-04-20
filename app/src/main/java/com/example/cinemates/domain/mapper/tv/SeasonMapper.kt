package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.SeasonDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Season


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun SeasonDTO.mapToSeason():Season{
    return Season(
        airDate = this.airDate,
        episodeCount = this.episodeCount,
        id = this.id,
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath,
        seasonNumber = this.seasonNumber
    )
}