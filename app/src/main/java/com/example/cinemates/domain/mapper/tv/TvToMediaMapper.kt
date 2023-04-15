package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.TvShowDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Media
import com.example.cinemates.util.MediaType


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class TvToMediaMapper : Mapper<TvShowDTO, Media> {
    override fun map(input: TvShowDTO): Media {
        return Media(
            mediaType = MediaType.TV,
            id = input.id,
            title = input.name,
            backdropPath = input.backdropPath,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage
        )
    }

}