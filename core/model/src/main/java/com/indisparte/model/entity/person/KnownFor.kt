package com.indisparte.model.entity.person

import com.indisparte.model.MediaType


data class KnownFor(
    val adult: Boolean?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>,
    val id: Int,
    private val mediaType: String,
    val name: String?,
    val originCountry: List<String>?,
    val originalLanguage: String,
    val originalName: String?,
    val originalTitle: String?,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double,
    val voteCount: Int,
) {
    val formattedMediaType: MediaType
        get() {
            val result = if (mediaType == MediaType.MOVIE.value) {
                MediaType.MOVIE
            } else {
                MediaType.TV
            }
            return result
        }
}