package com.example.cinemates.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Episode(
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

    override fun toString(): String {
        return "Episode(airDate='$airDate', episodeNumber=$episodeNumber, id=$id, name='$name', overview='$overview', production_code='$production_code', seasonNumber=$seasonNumber, stillPath='$stillPath', voteAverage=$voteAverage, voteCount=$voteCount)"
    }
}