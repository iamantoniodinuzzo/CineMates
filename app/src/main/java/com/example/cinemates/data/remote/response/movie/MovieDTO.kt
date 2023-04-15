package com.example.cinemates.data.remote.response.movie


import com.example.cinemates.data.remote.response.common.GenreDTO
import kotlinx.serialization.SerialName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Depicts a movie with generic information
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class MovieDTO(
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("genre_ids")
    val genres: List<GenreDTO> = listOf(),
    val id: Int = 0,
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String? = "",
    @SerialName("release_date")
    private val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
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

    val formattedReleaseDate: String
        get() {
            return formatDate(releaseDate)
        }

}