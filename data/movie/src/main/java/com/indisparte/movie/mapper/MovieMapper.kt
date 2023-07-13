package com.indisparte.movie.mapper

import com.indisparte.model.entity.Movie
import com.indisparte.movie.response.MovieDTO

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun MovieDTO.toMovie(): Movie {
    return Movie(
        adult = this.adult,
        posterPath = this.posterPath,
        id = this.id,
        popularity = this.popularity,
        releaseDate = this.formattedReleaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}