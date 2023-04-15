package com.example.cinemates.data.remote.response.tvShow


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Depicts a tvShow with generic information
 * @author Antonio Di Nuzzo (Indisparte)
 */
@Serializable
data class MediaTvDTO(
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("first_air_date")
    val firstAirDate: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
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
)