package com.indisparte.model.entity.movie

import com.indisparte.model.entity.common.Genre
import com.indisparte.model.entity.common.ProductionCompany
import com.indisparte.model.entity.common.ProductionCountry
import com.indisparte.model.entity.common.SpokenLanguage
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


class MovieDetails(
    adult: Boolean,
    private val backdropPath: String?,
    val belongsToCollection: BelongsToCollection?,
    private val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    popularity: Double,
    posterPath: String?,
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
) {

    val formattedBudget: String
        get() = formatCurrency(budget)

    val formattedRevenue: String
        get() = formatCurrency(revenue)

    val formattedRuntime: String
        get() = formatRuntime(runtime)

    val completeBackdropPathW780: String?
        get() = getCompleteImagePath(backdropPath)

    val productCompaniesConcatenatedString: String
        get() = productionCompanies.joinToString(", ") { it.name }

    fun formatCurrency(amount: Long): String {
        if (amount == 0L)
            return ""

        val currency = Currency.getInstance(Locale.getDefault())
        val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        formatter.currency = currency

        return when {
            amount >= 1_000_000_000 -> "${amount / 1_000_000_000} mld"
            amount >= 1_000_000 -> "${amount / 1_000_000} mln"
            else -> formatter.format(amount)
        }
    }

    override fun toString(): String {
        return "MovieDetails(backdropPath='$backdropPath', belongsToCollection=$belongsToCollection, budget=$budget, genres=$genres, homepage='$homepage', originalLanguage='$originalLanguage', originalTitle='$originalTitle', overview='$overview', productionCompanies=$productionCompanies, productionCountries=$productionCountries, revenue=$revenue, runtime=$runtime, spokenLanguages=$spokenLanguages, status='$status', tagline='$tagline', video=$video, voteCount=$voteCount)"
    }


}