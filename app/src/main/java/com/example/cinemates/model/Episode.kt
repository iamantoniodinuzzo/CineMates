package com.example.cinemates.model

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode_number")
    val episodeNumber: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    @SerializedName("season_number")
    val seasonNumber: Int,
    @SerializedName("still_path")
    val stillPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
){
    override fun toString(): String {
        return "Episode(airDate='$airDate', episodeNumber=$episodeNumber, id=$id, name='$name', overview='$overview', production_code='$production_code', seasonNumber=$seasonNumber, stillPath='$stillPath', voteAverage=$voteAverage, voteCount=$voteCount)"
    }
}