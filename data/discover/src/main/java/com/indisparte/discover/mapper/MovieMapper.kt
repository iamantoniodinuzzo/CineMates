package com.indisparte.discover.mapper

import com.indisparte.response.MovieDTO

/**
 * @author Antonio Di Nuzzo
 */
fun MovieDTO.toMovie(): com.indisparte.movie_data.Movie {
    return com.indisparte.movie_data.Movie(
        adult = this.adult,
        posterPath = this.posterPath,
        id = this.id,
        popularity = this.popularity,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}