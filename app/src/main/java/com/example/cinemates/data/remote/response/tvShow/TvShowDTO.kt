package com.example.cinemates.data.remote.response.tvShow


import com.example.cinemates.data.remote.response.common.GenreDTO
import kotlinx.serialization.SerialName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Depicts a tvShow with generic information
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class TvShowDTO(
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("first_air_date")
    private val firstAirDate: String = "",
    @SerialName("genre_ids")
    val genres: List<GenreDTO> = listOf(),
    val id: Int = 0,
    val name: String = "",
    @SerialName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_name")
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
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