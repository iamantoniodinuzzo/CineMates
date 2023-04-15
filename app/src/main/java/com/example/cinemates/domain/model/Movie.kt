package com.example.cinemates.domain.model

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
data class Movie(
    val belongsToCollection: Collection?,
    val genres: List<Genre> ,
    val id: Int,
    val posterPath: String?,
    val releaseDate: String,
    val runtime: String,
    val title: String,
    val voteAverage: Double,
    val originalTitle: String,
    val originalLanguage: String,
    val homepage: String,
    val backdropPath: String?,
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
)
