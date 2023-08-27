package com.indisparte.tv

import com.indisparte.base.Media


/**
 * Represents an episode of a TV show.
 *
 * @property airDate The air date of the episode in "yyyy-MM-dd" format.
 * @property episodeNumber The episode number.
 * @property id The unique ID associated with the episode.
 * @property name The name of the episode.
 * @property overview A brief overview or summary of the episode.
 * @property runtime The duration of the episode in minutes.
 * @property seasonNumber The season number to which the episode belongs.
 * @property showId The ID of the TV show to which the episode belongs.
 * @property stillPath The path to the still image of the episode.
 * @property voteAverage The average vote rating for the episode.
 * @author Antonio Di Nuzzo
 */
open class Episode(
    private val airDate: String,
    val episodeNumber: Int,
    id: Int,
    val name: String,
    val overview: String,
    private val runtime: Int,
    val seasonNumber: Int,
    val showId: Int,
    stillPath: String?,
    voteAverage: Double,
) : Media(
    id = id, mediaName = name, popularity = null, posterPath = stillPath, voteAverage = voteAverage
) {
    /**
     * Returns the formatted air date of the episode.
     */
    val formattedAirDate: String?
        get() {
            return formatDate(airDate)
        }

    /**
     * Returns the formatted runtime of the episode.
     */
    val formattedRuntime: String
        get() {
            return formatRuntime(runtime)
        }
}
