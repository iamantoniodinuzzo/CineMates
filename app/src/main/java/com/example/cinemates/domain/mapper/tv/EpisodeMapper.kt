package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.EpisodeDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Episode


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class EpisodeMapper : Mapper<EpisodeDTO, Episode> {
    override fun map(input: EpisodeDTO): Episode {
        return Episode(
            airDate = input.formattedAirDate,
            episodeNumber = input.episodeNumber,
            id = input.id,
            name = input.name,
            overview = input.overview,
            seasonNumber = input.seasonNumber,
            stillPath = input.stillPath,
            voteAverage = input.voteAverage
        )
    }

}*/

fun EpisodeDTO.mapToEpisode():Episode{
    return Episode(
        airDate = this.formattedAirDate,
        episodeNumber = this.episodeNumber,
        id = this.id,
        name = this.name,
        overview = this.overview,
        seasonNumber = this.seasonNumber,
        stillPath = this.stillPath,
        voteAverage = this.voteAverage
    )
}