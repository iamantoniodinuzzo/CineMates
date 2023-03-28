package com.example.cinemates.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Movie(
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Collection?,
    val genres: List<Genre> = listOf(),
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    private val runtime: Int?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    var personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    var favorite: Boolean = false,
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
    val budget: Int,
    val popularity: Double,
    val adult: Boolean,
    val revenue: Int?,
    val status: String,
    val tagline: String?,
    val video: Boolean,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>
) : Serializable {

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

    val formattedGenres: String
        get() {
            return if (genres.isNotEmpty()) {
                val result = genres.map { genre -> genre.name }
                result.joinToString(separator = ", ")
            } else "Not specified"
        }

    private fun formattedMoney(toFormat: Int?): String {
        return if (toFormat != 0) {
            val current = Locale.getDefault()
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency =
                Currency.getInstance(Currency.getInstance(current).currencyCode)
            format.format(toFormat)
        } else "Not specified"
    }

    val formattedBudget: String
        get() {
            return formattedMoney(budget)
        }

    val formattedRevenue: String
        get() {
            return formattedMoney(revenue)
        }

    val formattedReleaseDate: String
        get() {
            return if (releaseDate != null) {
                val localDate =
                    LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val pattern = DateTimeFormatter.ofPattern("MMM yyyy")
                pattern.format(localDate)
            } else "Not specified"
        }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false

        return true
    }

    override fun toString(): String {
        return "Movie(belongs_to_collection=$belongsToCollection, genres=$genres, id=$id, poster_path=$posterPath, release_date=$releaseDate, runtime=$runtime, title=$title, vote_average=$voteAverage, personalStatus=$personalStatus, favorite=$favorite, original_title=$originalTitle, original_language=$originalLanguage, homepage=$homepage, imdb_id=$imdbId, backdrop_path=$backdropPath, overview=$overview, budget=$budget, popularity=$popularity, adult=$adult, revenue=$revenue, status=$status, tagline=$tagline, video=$video, vote_count=$voteCount, production_companies=$productionCompanies, production_countries=$productionCountries)"
    }


}

enum class PersonalStatus(
    val status: Int
) {
    TO_SEE(1), SEEN(0), EMPTY(2);
}