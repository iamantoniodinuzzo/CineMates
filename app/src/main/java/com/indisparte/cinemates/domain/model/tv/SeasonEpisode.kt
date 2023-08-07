package com.indisparte.cinemates.domain.model.tv


import com.indisparte.cinemates.domain.model.credits.Crew

class SeasonEpisode(
    airDate: String,
    val crew: List<Crew>,
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