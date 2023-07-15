package com.indisparte.cinemates.domain.model.tv


open class Season(
    val airDate: String,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int
):java.io.Serializable