package com.indisparte.model.entity


open class TvShow(
    val id: Int,
    val name: String,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
)