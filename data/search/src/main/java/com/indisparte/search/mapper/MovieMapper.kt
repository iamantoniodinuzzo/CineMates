package com.indisparte.search.mapper

import com.indisparte.model.entity.Movie
import com.indisparte.search.response.MovieDTO

/**
 * @author Antonio Di Nuzzo
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
