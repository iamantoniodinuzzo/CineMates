package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.SeasonDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Season


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SeasonMapper : Mapper<SeasonDTO, Season> {
    override fun map(input: SeasonDTO): Season {
        return Season(
            airDate = input.airDate,
            episodeCount = input.episodeCount,
            id = input.id,
            name = input.name,
            overview = input.overview,
            posterPath = input.posterPath,
            seasonNumber = input.seasonNumber
        )
    }

}