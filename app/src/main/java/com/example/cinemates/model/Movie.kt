package com.example.cinemates.model

import java.io.Serializable
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Movie(
    val belongs_to_collection: Collection?,
    val genres: List<Genre> = listOf(),
    val id: Int,
    val poster_path: String?,
    val release_date: String?,
    val runtime: Int?,
    val title: String?,
    val vote_average: Double?,
    var personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    var favorite: Boolean = false,
    val original_title: String?,
    val original_language: String?,
    val homepage: String?,
    val imdb_id: String?,
    val backdrop_path: String?,
    val overview: String?,
    val budget: Int?,
    val popularity: Double?,
    val adult: Boolean?,
    val revenue: Int?,
    val status: String?,
    val tagline: String?,
    val video: Boolean?,
    val vote_count: Int?,
    val production_companies: List<ProductionCompany>?,
    val production_countries: List<ProductionCountry>?
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
            return if (release_date != null) {
                val localDate =
                    LocalDate.parse(release_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
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
        return "Movie(belongs_to_collection=$belongs_to_collection, genres=$genres, id=$id, poster_path=$poster_path, release_date=$release_date, runtime=$runtime, title=$title, vote_average=$vote_average, personalStatus=$personalStatus, favorite=$favorite, original_title=$original_title, original_language=$original_language, homepage=$homepage, imdb_id=$imdb_id, backdrop_path=$backdrop_path, overview=$overview, budget=$budget, popularity=$popularity, adult=$adult, revenue=$revenue, status=$status, tagline=$tagline, video=$video, vote_count=$vote_count, production_companies=$production_companies, production_countries=$production_countries)"
    }


}
enum class PersonalStatus(
    val status: Int
) {
    TO_SEE(1), SEEN(0), EMPTY(2);
}