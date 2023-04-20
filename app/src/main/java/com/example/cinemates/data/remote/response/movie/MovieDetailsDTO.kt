package com.example.cinemates.data.remote.response.movie


import com.example.cinemates.data.remote.response.common.GenreDTO
import com.example.cinemates.data.remote.response.common.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.common.ProductionCountryDTO
import com.example.cinemates.data.remote.response.common.SpokenLanguageDTO
import com.google.gson.annotations.SerializedName
import java.text.NumberFormat
import java.util.*

enum class Status {
    Rumored, Planned, InProduction, PostProduction, Released, Canceled
}

class MovieDetailsDTO(
    adult: Boolean,
    backdropPath: String,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: CollectionDTO?,
    private val budget: Int,
    genres: List<GenreDTO>,
    val homepage: String?,
    id: Int = 0,
    @SerializedName("imdb_id")
    val imdbId: String?,
    originalLanguage: String,
    originalTitle: String,
    overview: String,
    popularity: Double,
    posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDTO>?,
    releaseDate: String,
    private val revenue: Long,
    private val runtime: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDTO>?,
    val status: String,
    val tagline: String?,
    title: String,
    video: Boolean,
    voteAverage: Double,
    voteCount: Int
) : MovieDTO(
    adult,
    backdropPath,
    genres,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount
) {
    private fun formatCurrency(number: Long, locale: Locale = Locale.getDefault()): String {
        val formatter = NumberFormat.getCurrencyInstance(locale)
        return formatter.format(number)
    }

    private fun formatTimeMeasurement(timeMeasurement: Int): String {
        val hours = timeMeasurement / 60
        val minutes = timeMeasurement % 60
        return String.format("%02d h %02d min", hours, minutes)
    }

    val formattedBudget: String
        get() {
            return if (budget != 0) {
                formatCurrency(budget.toLong())
            } else {
                "Not specified"
            }
        }

    val formattedRevenue: String
        get() {
            return formatCurrency(revenue)

        }

    val formattedRuntime: String
        get() {
            return runtime?.let {
                formatTimeMeasurement(runtime)
            } ?: "Not specified"

        }
}