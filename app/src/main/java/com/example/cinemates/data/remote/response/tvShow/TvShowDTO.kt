package com.example.cinemates.data.remote.response.tvShow


import com.example.cinemates.data.remote.response.common.GenreDTO
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Depicts a tvShow with generic information
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class TvShowDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("first_air_date")
    private val firstAirDate: String,
    @SerialName("genre_ids")
    val genres: List<GenreDTO> ,
    val id: Int,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    private fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat.getDateInstance()
        val formattedDate = inputFormat.parse(date)
        return outputFormat.format(formattedDate)
    }

    val formattedFirstAirDate: String
        get() {
            return if (firstAirDate.isNotEmpty()) {
                formatDate(firstAirDate)
            } else {
                "Not specified"
            }
        }
}