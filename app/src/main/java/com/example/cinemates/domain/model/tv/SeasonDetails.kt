package com.example.cinemates.domain.model.tv


data class SeasonDetails(
    val airDate: String,
    val seasonEpisodes: List<SeasonEpisode>,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int
)