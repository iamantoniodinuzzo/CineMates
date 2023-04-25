package com.example.cinemates.domain.model


import com.example.cinemates.data.remote.response.tvShow.SeasonEpisodeDTO

data class SeasonDetails(
    val airDate: String,
    val seasonEpisodes: List<SeasonEpisode>,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int
)