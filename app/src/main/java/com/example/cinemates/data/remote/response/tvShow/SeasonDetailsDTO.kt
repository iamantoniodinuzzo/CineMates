package com.example.cinemates.data.remote.response.tvShow


import com.google.gson.annotations.SerializedName

class SeasonDetailsDTO(
    airDate: String,
    @SerializedName("episodes")
    val seasonEpisodes: List<SeasonEpisodeDTO>,
    @SerializedName("_id")
    val idString: String,
    id: Int,
    name: String,
    overview: String,
    posterPath: String,
    seasonNumber: Int
) : SeasonDTO(
    airDate,
    id,
    name,
    overview,
    posterPath, seasonNumber
)