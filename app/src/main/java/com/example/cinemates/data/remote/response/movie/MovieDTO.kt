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
    val backdropPath: String?,
    @SerialName("genre_ids")
    val genres: List<GenreDTO> ,
    val id: Int ,
    @SerialName("original_language")
    val originalLanguage: String ,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    private val releaseDate: String,
    val title: String ,
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double ,
    @SerialName("vote_count")
    val voteCount: Int
) {
    private fun formatDate(date: String): String {
        return if(date.isNotEmpty()) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat.getDateInstance()
            val formattedDate = inputFormat.parse(date)
            outputFormat.format(formattedDate)
        }else{
            "Not specified"
        }
    }

    override fun toString(): String {
        return "MovieDTO(adult=$adult, backdropPath='$backdropPath', genres=$genres, id=$id, originalLanguage='$originalLanguage', originalTitle='$originalTitle', overview='$overview', popularity=$popularity, posterPath=$posterPath, releaseDate='$releaseDate', title='$title', video=$video, voteAverage=$voteAverage, voteCount=$voteCount)"
    }

    val formattedReleaseDate: String
        get() {
            return formatDate(releaseDate)
        }


}