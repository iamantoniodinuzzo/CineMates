package com.indisparte.media_search.mapper

import com.indisparte.model.entity.movie.Movie
import com.indisparte.response.MovieDTO

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
