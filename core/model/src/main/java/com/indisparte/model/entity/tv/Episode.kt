package com.indisparte.model.entity.tv

import com.indisparte.model.entity.base.Media


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
    val formattedAirDate: String?
        get() {
            return formatDate(airDate)
        }

    val formattedRuntime: String
        get() {
            return formatRuntime(runtime)
        }
}