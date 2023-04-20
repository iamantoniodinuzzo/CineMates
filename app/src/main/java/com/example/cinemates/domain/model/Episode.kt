package com.example.cinemates.domain.model


data class Episode(
    val airDate: String,
    val episodeNumber: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val seasonNumber: Int,
    val stillPath: String,
    val voteAverage: Double,
)