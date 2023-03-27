package com.example.cinemates.model

import com.example.cinemates.util.MediaType
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Movie(
    val belongs_to_collection: Collection?,
    genres: List<Genre> = listOf(),
    id: Int,
    poster_path: String,
    val release_date: String?,
    val runtime: Int?,
    title: String,
    vote_average: Double,
    personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    favorite: Boolean = false,
    val original_title: String,
    original_language: String,
    homepage: String,
    val imdb_id: String?,
    backdrop_path: String,
    overview: String,
    val budget: Int,
    popularity: Double,
    val adult: Boolean,
    val revenue: Int,
    status: String,
    tagline: String,
    val video: Boolean,
    vote_count: Int,
     production_companies: List<ProductionCompany>,
     production_countries: List<ProductionCountry>
) : Media(
    backdrop_path,
    genres,
    id,
    original_language,
    poster_path,
    popularity,
    vote_average,
    vote_count,
    title,
    personalStatus,
    favorite,
    tagline,
    status,
    overview,
    homepage,
    production_companies,
    production_countries
) {

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

    val formattedBudget: String
        get() {
            return formatMoney()
        }

    private fun formatMoney() = if (budget != 0) {
        val current = Locale.getDefault()
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency =
            Currency.getInstance(Currency.getInstance(current).currencyCode)
        format.format(budget)
    } else ""

    val formattedRevenue: String
        get() {
            return formatMoney()
        }

    val formattedReleaseDate: String
        get() {
            return if (release_date != null) {
                val localDate =
                    LocalDate.parse(release_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val pattern = DateTimeFormatter.ofPattern("MMM yyyy")
                pattern.format(localDate)
            } else ""
        }

    override fun getType(): MediaType {
        return MediaType.MOVIE
    }


}
