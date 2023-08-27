package com.indisparte.model.entity.tv

import com.indisparte.model.entity.base.TMDBItem
import com.indisparte.model.util.Constants


open class Season(
    private val airDate: String,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    private val posterPath: String?,
    val seasonNumber: Int,
    val voteAverage: Int,
) : TMDBItem() {
    val completePosterPathW780: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W780, posterPath)

    val completePosterPathW500: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W500, posterPath)

    val formattedAirDate: String?
        get() = formatDate(airDate)
}
