package com.indisparte.model.entity

import com.indisparte.model.util.Constants


data class CollectionDetails(
    private val backdropPath: String?,
    val id: Int,
    val name: String,
    val overview: String?,
    val parts: List<Movie>,
    private val posterPath: String?,
){
    val completePosterPathW780: String?
        get() = if (posterPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W780}$posterPath"

    val completePosterPathW500: String?
        get() = if (posterPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W500}$posterPath"

    val completeBackdropPathW780: String?
        get() = if (posterPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W780}$backdropPath"

    val completeBackdropPathW500: String?
        get() = if (posterPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W500}$backdropPath"

}