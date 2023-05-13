package com.example.cinemates.domain.model.tv


import com.example.cinemates.data.remote.response.credits.CrewDTO

class SeasonEpisode(
    airDate: String,
    val crew: List<CrewDTO>,
    episodeNumber: Int,
    val guestStar: List<GuestStar>,
    id: Int,
    name: String,
    overview: String,
    seasonNumber: Int,
    stillPath: String,
    voteAverage: Double,
) : Episode(
    airDate,
    episodeNumber,
    id,
    name,
    overview,
    seasonNumber,
    stillPath,
    voteAverage,
)