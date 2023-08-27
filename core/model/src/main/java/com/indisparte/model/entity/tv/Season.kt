package com.indisparte.model.entity.tv

import com.indisparte.model.util.Constants


open class Season(
    val airDate: String,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    private val posterPath: String?,
    val seasonNumber: Int,
    val voteAverage: Int,
) {
    val completePosterPathW780: String?
        get() = if (posterPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W780}$posterPath"

    val completePosterPathW500: String?
        get() = if (posterPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W500}$posterPath"
}
