package com.example.cinemates.domain.mapper.movie

import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Media
import com.example.cinemates.util.MediaType


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieToMediaMapper: Mapper<MovieDTO, Media> {
    override fun map(input: MovieDTO): Media {
        return Media(
            mediaType = MediaType.MOVIE,
            id = input.id,
            title= input.title,
            posterPath =  input.posterPath,
            backdropPath = input.backdropPath,
            voteAverage = input.voteAverage
        )
    }

}