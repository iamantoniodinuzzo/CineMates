package com.example.cinemates.domain.model

import com.example.cinemates.data.remote.response.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.ProductionCountryDTO
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

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
