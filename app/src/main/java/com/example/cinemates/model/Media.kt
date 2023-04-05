package com.example.cinemates.model

import com.example.cinemates.util.MediaType
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
open  class Media(
    val mediaType: MediaType,
    val id: Int,
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val homepage: String,
    val overview: String?,
    val popularity: Double,
    val status: String,
    val tagline: String?,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    var favorite: Boolean = false,
    var personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    val genres: List<Genre>? = listOf()
) : Serializable {

    val formattedGenres: String
        get() {
            return genres?.let {
                val result = it.map { genre -> genre.name }
                result.joinToString(separator = ", ")
            } ?: "Not specified"
        }

    protected fun formattedMoney(toFormat: Long): String {
        val current = Locale.getDefault()
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency =
            Currency.getInstance(Currency.getInstance(current).currencyCode)
        return format.format(toFormat)

    }

    protected fun formatDate(dateToFormat: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(dateToFormat)
        return date?.let { outputFormat.format(it) } ?: "Not specified"
    }


    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Media

        if (id != other.id) return false

        return true
    }

    override fun toString(): String {
        return "Media(id=$id, title=$title, posterPath=$posterPath, backdropPath=$backdropPath, voteAverage=$voteAverage, originalTitle='$originalTitle', originalLanguage='$originalLanguage', homepage='$homepage', overview=$overview, popularity=$popularity, status='$status', tagline=$tagline, voteCount=$voteCount, productionCompanies=$productionCompanies, productionCountries=$productionCountries, favorite=$favorite, personalStatus=$personalStatus, genres=$genres)"
    }


}

enum class PersonalStatus(
    val status: Int
) {
    TO_SEE(1), SEEN(0), EMPTY(2);
}