package com.indisparte.model.entity.movie

import com.indisparte.model.entity.base.Media

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class Movie(
    val adult: Boolean,
    id: Int,
    popularity: Double,
    posterPath: String?,
    private val releaseDate: String,
    val title: String,
    voteAverage: Double,
) : Media(
    id = id,
    mediaName = title,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage
) {
    override fun toString(): String {
        return "Movie(adult=$adult, releaseDate='$releaseDate', title='$title')"
    }

    val formattedReleaseDate: String?
        get() {
            return formatDate(releaseDate)
        }
}