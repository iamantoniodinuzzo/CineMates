package com.example.cinemates.data.remote.response.tvShow

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

open class SeasonDTO(
    @SerializedName("air_date")
    protected val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int
) {

    constructor(
        airDate: String?,
        id: Int,
        name: String,
        overview: String,
        posterPath: String?,
        seasonNumber: Int
    ) : this(airDate, 0, id, name, overview, posterPath, seasonNumber)

    protected fun dateFormatter(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat.getDateInstance()
        val formattedDate = inputFormat.parse(date)
        return outputFormat.format(formattedDate)
    }

    val formattedAirDate: String
        get() {
            return airDate?.let {
                dateFormatter(it)
            } ?: ""

        }
}