package com.indisparte.model.entity



data class SeasonDetails(
    val airDate: String,
    val episodes: List<Episode>,
    val id: String,
    val seasonId: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int,
    val voteAverage: Int
)