package com.indisparte.model.entity.movie

import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780


open class BelongsToCollection(
    private val backdropPath: String?,
    val id: Int,
    val name: String,
    private val posterPath: String?,
) {
    val completePosterPathW780: String?
        get() = if (posterPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W780$posterPath"

    val completePosterPathW500: String?
        get() = if (posterPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W500$posterPath"
    val completeBackdropPathW780: String?
        get() = if (backdropPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W780$backdropPath"

    val completeBackdropPathW500: String?
        get() = if (backdropPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W500$backdropPath"

}