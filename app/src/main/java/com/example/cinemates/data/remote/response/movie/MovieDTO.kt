package com.example.cinemates.data.remote.response.movie

import com.example.cinemates.data.remote.response.GenreDTO
import com.example.cinemates.data.remote.response.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.ProductionCountryDTO
import com.example.cinemates.domain.model.*
import com.example.cinemates.util.MediaType
import com.google.gson.annotations.SerializedName
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

data class MovieDTO(
    @SerializedName("belongs_to_collection")
    val belongsToCollection: CollectionDTO?,
    val genre: List<GenreDTO> = listOf(),
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    private val releaseDate: String?,
    private val runtime: Int?,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val homepage: String,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val overview: String?,
    private val budget: Long,
    val popularity: Double,
    val adult: Boolean,
    private val revenue: Long?,
    val status: String,
    val tagline: String?,
    val video: Boolean,
    val voteCount: Int,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDTO>
){
    private fun formattedMoney(toFormat: Long): String {
        val current = Locale.getDefault()
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency =
            Currency.getInstance(Currency.getInstance(current).currencyCode)
        return format.format(toFormat)

    }

    private fun formatDate(dateToFormat: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(dateToFormat)
        return date?.let { outputFormat.format(it) } ?: "Not specified"
    }

    val formattedRuntime: String
        get() {
            return if (runtime != null && runtime != 0) {
                val hours = (runtime / 60) //since both are ints, you get an int
                val minutes = (runtime % 60)
                if (hours == 0) {
                    String.format("%02d min", minutes)
                } else {
                    String.format("%d hrs %02d min", hours, minutes)
                }
            } else ""
        }



    val formattedBudget: String
        get() {
            return formattedMoney(budget)
        }

    val formattedRevenue: String
        get() {
            return revenue?.let {
                formattedMoney(revenue)
            } ?: "Not specified"
        }

    val formattedReleaseDate: String
        get() {
            return releaseDate?.let { formatDate(it) } ?: "Not specified"
        }
}

