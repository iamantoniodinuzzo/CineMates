package com.example.cinemates.domain.mapper.movie

import com.example.cinemates.data.remote.response.movie.MovieDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Media
import com.example.cinemates.util.MediaType


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun MovieDTO.mapToMedia():Media{
    return Media(
        mediaType = MediaType.MOVIE,
        id = this.id,
        title= this.title,
        posterPath =  this.posterPath,
        backdropPath = this.backdropPath,
        voteAverage = this.voteAverage
    )
}