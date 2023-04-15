package com.example.cinemates.data.remote.response.movie


import com.example.cinemates.data.remote.response.GenreDTO
import com.example.cinemates.data.remote.response.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.ProductionCountryDTO
import com.example.cinemates.data.remote.response.SpokenLanguageDTO
import kotlinx.serialization.SerialName
import java.text.NumberFormat
import java.util.*

class MovieDetailsDTO(
    adult: Boolean = false,
    backdropPath: String = "",
    @SerialName("belongs_to_collection")
    val belongsToCollection: CollectionDTO? = null,
    private val budget: Int = 0,
    genres: List<GenreDTO> = listOf(),
    val homepage: String = "",
    id: Int = 0,
    @SerialName("imdb_id")
    val imdbId: String = "",
    originalLanguage: String = "",
    originalTitle: String = "",
    overview: String = "",
    popularity: Double = 0.0,
    posterPath: String? = "",
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO> = listOf(),
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDTO> = listOf(),
    releaseDate: String = "",
    private val revenue: Int = 0,
    private val runtime: Int = 0,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDTO> = listOf(),
    val status: String = "",
    val tagline: String = "",
    title: String = "",
    video: Boolean = false,
    voteAverage: Double = 0.0,
    voteCount: Int = 0
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
    private fun formatCurrency(number: Int, locale: Locale = Locale.getDefault()): String {
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
                formatCurrency(budget)
            } else {
                "Not specified"
            }
        }

    val formattedRevenue: String
        get() {
            return if (revenue != 0) {
                formatCurrency(revenue)
            } else {
                "Not specified"
            }
        }

    val formattedRuntime: String
        get() {
            return if (runtime != 0) {
                formatTimeMeasurement(runtime)
            } else {
                "Not specified"
            }
        }
}