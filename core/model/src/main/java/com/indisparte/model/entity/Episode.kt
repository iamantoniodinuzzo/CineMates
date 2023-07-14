package com.indisparte.model.entity



data class Episode(
    val airDate: String,
    val episodeNumber: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val runtime: Int,
    val seasonNumber: Int,
    val showId: Int,
    val stillPath: String?,
    val voteAverage: Int,
)