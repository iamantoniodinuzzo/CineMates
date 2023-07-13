package com.indisparte.model.entity

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
data class Movie(
    val adult: Boolean,
    val id: Int,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)
