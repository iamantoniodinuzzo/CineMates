package com.indisparte.media_search.mapper

import com.indisparte.movie_data.Movie
import com.indisparte.response.MovieDTO
import com.indisparte.response.TvShowDTO
import com.indisparte.tv.TvShow

/**
 * @author Antonio Di Nuzzo
 */
fun MovieDTO.toMovie(): Movie {
    return Movie(
        adult = this.adult,
        posterPath = this.posterPath,
        id = this.id,
        popularity = this.popularity,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}

fun TvShowDTO.toTvShow(): TvShow {
    return TvShow(
        id = this.id,
        name = this.name,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage
    )
}
