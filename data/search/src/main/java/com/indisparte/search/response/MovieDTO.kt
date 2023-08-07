package com.indisparte.search.response


import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Depicts a movie with generic information
 * @author Antonio Di Nuzzo (Indisparte)
 */
data class MovieDTO(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genres: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    private val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    protected fun dateFormatter(date: String): String {
        return if (date.isNotEmpty()) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat.getDateInstance()
            val formattedDate = inputFormat.parse(date)
            outputFormat.format(formattedDate)
        } else {
            ""
        }
    }

    override fun toString(): String {
        return "MovieDTO(adult=$adult, backdropPath=$backdropPath, genres=$genres, id=$id, originalLanguage='$originalLanguage', originalTitle='$originalTitle', overview='$overview', popularity=$popularity, posterPath=$posterPath, releaseDate='$releaseDate', title='$title', video=$video, voteAverage=$voteAverage, voteCount=$voteCount)"
    }


    val formattedReleaseDate: String
        get() {
            return dateFormatter(releaseDate)
        }


}