package com.indisparte.response


import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Depicts a movie with generic information
 * @author Antonio Di Nuzzo
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
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)