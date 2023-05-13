package com.example.cinemates.data.remote.response.tvShow


import com.example.cinemates.data.remote.response.credits.CrewDTO
import com.google.gson.annotations.SerializedName

class SeasonEpisodeDTO(
    airDate: String,
    val crew: List<CrewDTO>,
    episodeNumber: Int,
    @SerializedName("guest_stars")
    val guestStar: List<GuestStarDTO>,
    id: Int,
    name: String,
    overview: String,
    productionCode: String,
    seasonNumber: Int,
    stillPath: String,
    voteAverage: Double,
    voteCount: Int
) : EpisodeDTO(
    airDate,
    episodeNumber,
    id,
    name,
    overview,
    productionCode,
    seasonNumber,
    stillPath,
    voteAverage,
    voteCount
)