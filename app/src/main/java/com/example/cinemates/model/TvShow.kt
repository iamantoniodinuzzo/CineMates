package com.example.cinemates.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class TvShow(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("created_by")
    val createdBy: List<CreatedBy>,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    private val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerializedName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerializedName("last_air_date")
    val lastAirDate: String,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: Episode,
    val name: String,
    val networks: List<Network>,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Episode?,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) : java.io.Serializable {


    val formattedFirstAirDate: String
        get() {
            return formatDate(firstAirDate)
        }

    val formattedLastAirDate: String
        get() {
            return formatDate(lastAirDate)
        }

    private fun formatDate(dateToFormat: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(dateToFormat)
        return date?.let { outputFormat.format(it) } ?: "Not specified"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TvShow) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "TvShow(backdropPath=$backdropPath, createdBy=$createdBy, episodeRunTime=$episodeRunTime, firstAirDate='$firstAirDate', genres=$genres, homepage='$homepage', id=$id, inProduction=$inProduction, languages=$languages, lastAirDate='$lastAirDate', lastEpisodeToAir=$lastEpisodeToAir, name='$name', networks=$networks, nextEpisodeToAir=$nextEpisodeToAir, numberOfEpisodes=$numberOfEpisodes, numberOfSeasons=$numberOfSeasons, originCountry=$originCountry, originalLanguage='$originalLanguage', originalName='$originalName', overview='$overview', popularity=$popularity, posterPath=$posterPath, productionCompanies=$productionCompanies, productionCountries=$productionCountries, seasons=$seasons, spokenLanguages=$spokenLanguages, status='$status', tagline='$tagline', type='$type', voteAverage=$voteAverage, voteCount=$voteCount)"
    }

}