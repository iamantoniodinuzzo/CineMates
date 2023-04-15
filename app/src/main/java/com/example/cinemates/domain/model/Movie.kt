package com.example.cinemates.domain.model

import com.example.cinemates.util.MediaType

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class Movie(
    val belongsToCollection: Collection?,
    val genres: List<Genre>,
    id: Int,
    posterPath: String?,
    val releaseDate: String,
    val runtime: String,
    title: String,
    voteAverage: Double,
    val originalTitle: String,
    val originalLanguage: String,
    val homepage: String,
    backdropPath: String?,
    val overview: String?,
    val budget: String,
    val popularity: Double,
    val adult: Boolean,
    val revenue: String,
    val status: String,
    val tagline: String?,
    val video: Boolean,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>
) : Media(
    MediaType.MOVIE,
    id,
    title,
    posterPath,
    backdropPath,
    voteAverage
)
