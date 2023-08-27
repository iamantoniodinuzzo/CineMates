package com.indisparte.tv

import com.indisparte.base.Constants

/**
 * Represents a season of a TV show.
 *
 * @property airDate The air date of the season.
 * @property episodeCount The number of episodes in the season.
 * @property id The unique ID associated with the season.
 * @property name The name of the season.
 * @property overview An overview of the season.
 * @property posterPath The poster path of the season.
 * @property seasonNumber The season number.
 * @property voteAverage The average vote rating for the season.
 * @author Antonio Di Nuzzo
 */
open class Season(
    private val airDate: String,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    private val posterPath: String?,
    val seasonNumber: Int,
    val voteAverage: Int,
) : com.indisparte.base.TMDBItem() {
    val completePosterPathW780: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W780, posterPath)

    val completePosterPathW500: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W500, posterPath)

    val formattedAirDate: String?
        get() = formatDate(airDate)
}
