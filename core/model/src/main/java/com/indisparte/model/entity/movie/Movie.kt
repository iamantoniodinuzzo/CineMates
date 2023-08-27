package com.indisparte.model.entity.movie

import com.indisparte.model.entity.base.Media

/**
 * Represents a movie.
 *
 * @property adult Indicates whether the movie is intended for adult audiences.
 * @property id The unique identifier of the movie.
 * @property popularity The popularity rating of the movie.
 * @property posterPath The path to the poster image of the movie.
 * @property releaseDate The release date of the movie.
 * @property title The title of the movie.
 * @property voteAverage The average vote rating of the movie.
 * @author Antonio Di Nuzzo
 */
open class Movie(
    val adult: Boolean,
    id: Int,
    popularity: Double,
    posterPath: String?,
    protected val releaseDate: String?,
    val title: String,
    voteAverage: Double,
) : Media(
    id = id,
    mediaName = title,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage
) {
    /**
     * Gets the formatted release date of the movie.
     */
    val formattedReleaseDate: String?
        get() {
            return formatDate(releaseDate)
        }

    /**
     * Returns a string representation of the Movie object.
     */
    override fun toString(): String {
        return "Movie(adult=$adult, releaseDate='$releaseDate', title='$title')"
    }
}

