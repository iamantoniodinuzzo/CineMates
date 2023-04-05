package com.example.cinemates.model

import com.example.cinemates.util.MediaType
import com.google.gson.annotations.SerializedName
import java.util.*

class Movie(
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Collection?,
    genres: List<Genre> = listOf(),
    id: Int,
    posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    private val runtime: Int?,
    title: String?,
    voteAverage: Double,
    personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    favorite: Boolean = false,
    originalTitle: String,
    originalLanguage: String,
    homepage: String,
    @SerializedName("imdb_id")
    val imdbId: String?,
    backdropPath: String?,
    overview: String?,
    private val budget: Long,
    popularity: Double,
    val adult: Boolean,
    private val revenue: Long?,
    status: String,
    tagline: String?,
    val video: Boolean,
    voteCount: Int,
    productionCompanies: List<ProductionCompany>,
    productionCountries: List<ProductionCountry>
) : Media(
    MediaType.MOVIE,
    id,
    title,
    posterPath,
    backdropPath,
    voteAverage,
    originalTitle,
    originalLanguage,
    homepage,
    overview,
    popularity,
    status,
    tagline,
    voteCount,
    productionCompanies,
    productionCountries,
    favorite,
    personalStatus,
    genres
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


    override fun toString(): String {
        return "Movie(belongs_to_collection=$belongsToCollection, genres=$genres, id=$id, poster_path=$posterPath, release_date=$releaseDate, runtime=$runtime, title=$title, vote_average=$voteAverage, personalStatus=$personalStatus, favorite=$favorite, original_title=$originalTitle, original_language=$originalLanguage, homepage=$homepage, imdb_id=$imdbId, backdrop_path=$backdropPath, overview=$overview, budget=$budget, popularity=$popularity, adult=$adult, revenue=$revenue, status=$status, tagline=$tagline, video=$video, vote_count=$voteCount, production_companies=$productionCompanies, production_countries=$productionCountries)"
    }


}

