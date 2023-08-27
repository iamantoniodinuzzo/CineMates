package com.indisparte.model.entity.person



data class PersonAsCast(
    val character: String,
    val episodeCount: Int?,
    val firstAirDate: String?,
    val id: Int,
    val order: Int?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double
)