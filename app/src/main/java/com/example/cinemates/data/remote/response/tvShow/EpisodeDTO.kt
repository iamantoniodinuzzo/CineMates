package com.example.cinemates.data.remote.response.tvShow

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class EpisodeDTO(
    @SerializedName("air_date")
    private val airDate: String?,
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
) {

    val formattedAirDate: String
        get() {
            return airDate?.let {
                val localDate =
                    LocalDate.parse(airDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val pattern = DateTimeFormatter.ofPattern("MMM yyyy")
                 pattern.format(localDate)
            }?: "Not specified"


        }
}