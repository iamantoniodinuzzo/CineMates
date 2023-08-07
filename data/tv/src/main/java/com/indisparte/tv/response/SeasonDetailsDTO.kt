package com.indisparte.tv.response


import com.google.gson.annotations.SerializedName

data class SeasonDetailsDTO(
    @SerializedName("air_date")
    val airDate: String,
    val episodes: List<EpisodeDTO>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("id")
    val seasonId: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("season_number")
    val seasonNumber: Int,
    @SerializedName("vote_average")
    val voteAverage: Int
)