package com.indisparte.model.entity

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


class MovieDetails(
    adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: BelongsToCollection,
    private val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    popularity: Double,
    posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    releaseDate: String,
    private val revenue: Long,
    private val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    title: String,
    val video: Boolean,
    voteAverage: Double,
    val voteCount: Int,
) : Movie(
    adult = adult,
    id = id,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage
){
    val formattedBudget: String
        get() = formatCurrency(budget)

    val formattedRevenue: String
        get() = formatCurrency(revenue)

    val formattedRuntime: String
        get() = formatRuntime(runtime)

    private fun formatRuntime(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime % 60

        return "$hours h $minutes min"
    }
    private fun formatCurrency(amount: Long): String {
        val currency = Currency.getInstance(Locale.getDefault())
        val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        formatter.currency = currency
        return formatter.format(amount)
    }

}