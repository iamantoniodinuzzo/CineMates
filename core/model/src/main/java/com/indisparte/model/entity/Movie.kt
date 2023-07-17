package com.indisparte.model.entity

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class Movie(
    val adult: Boolean,
    id: Int,
    popularity: Double,
    posterPath: String?,
    val releaseDate: String,
    val title: String,
    voteAverage: Double,
) : Media(
    id = id,
    title,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage
){
    override fun toString(): String {
        return "Movie(adult=$adult, releaseDate='$releaseDate', title='$title')"
    }
}
