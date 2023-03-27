package com.example.cinemates.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("overview")
    val overview: String,
    @SerialName("production_code")
    val productionCode: Any,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("show_id")
    val showId: Int,
    @SerialName("still_path")
    val stillPath: String,
    @SerialName("vote_average")
    val voteAverage: Int,
    @SerialName("vote_count")
    val voteCount: Int
){
    override fun toString(): String {
        return "Episode(airDate='$airDate', episodeNumber=$episodeNumber, id=$id, name='$name', order=$order, overview='$overview', productionCode=$productionCode, seasonNumber=$seasonNumber, showId=$showId, stillPath='$stillPath', voteAverage=$voteAverage, voteCount=$voteCount)"
    }
}