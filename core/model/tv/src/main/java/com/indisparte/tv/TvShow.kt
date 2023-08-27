package com.indisparte.tv

import com.indisparte.base.Media

/**
 * Represents a TV show with basic information.
 *
 * @property id The unique ID associated with the TV show.
 * @property name The name of the TV show.
 * @property popularity The popularity of the TV show.
 * @property posterPath The poster path of the TV show.
 * @property voteAverage The average vote rating for the TV show.
 */
open class TvShow(
    id: Int,
    val name: String,
    popularity: Double,
    posterPath: String?,
    voteAverage: Double,
) : Media(
    id = id,
    name,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage
)