package com.example.cinemates.model.entities

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class Media(
    val backdrop_path: String?,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val vote_average: Double
)