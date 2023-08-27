package com.indisparte.model.entity.movie

import com.indisparte.model.entity.common.Genre
import com.indisparte.model.entity.common.ProductionCompany
import com.indisparte.model.entity.common.ProductionCountry
import com.indisparte.model.entity.common.SpokenLanguage
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780
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
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780,backdropPath)
    val completeBackdropPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, backdropPath)

    val productCompaniesConcatenatedString: String
        get() = productionCompanies.joinToString(", ") { it.name }


    override fun toString(): String {
        return "MovieDetails(backdropPath='$backdropPath', belongsToCollection=$belongsToCollection, budget=$budget, genres=$genres, homepage='$homepage', originalLanguage='$originalLanguage', originalTitle='$originalTitle', overview='$overview', productionCompanies=$productionCompanies, productionCountries=$productionCountries, revenue=$revenue, runtime=$runtime, spokenLanguages=$spokenLanguages, status='$status', tagline='$tagline', video=$video, voteCount=$voteCount)"
    }


}