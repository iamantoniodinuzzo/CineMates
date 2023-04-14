package com.example.cinemates.data.remote.response.tvShow

import com.example.cinemates.data.remote.response.GenreDTO
import com.example.cinemates.data.remote.response.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.ProductionCountryDTO
import com.example.cinemates.data.remote.response.SpokenLanguageDTO
import com.example.cinemates.domain.model.*
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class TvShowDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("created_by")
    val createdBy: List<CreatedByDTO>,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    private val firstAirDate: String,
    val genre: List<GenreDTO>,
    val homepage: String,
    val id: Int,
    @SerializedName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerializedName("last_air_date")
    private val lastAirDate: String,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeDTO?,
    val name: String,
    val network: List<NetworkDTO>,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeDTO?,
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
    val productionCompanies: List<ProductionCompanyDTO>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDTO>,
    val season: List<SeasonDTO>,
    @SerializedName("spoken_languages")
    val spokenLanguage: List<SpokenLanguageDTO>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
){
    private fun formatDate(dateToFormat: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(dateToFormat)
        return date?.let { outputFormat.format(it) } ?: "Not specified"
    }


    val formattedFirstAirDate: String
        get() {
            return formatDate(firstAirDate)
        }

    val formattedLastAirDate: String
        get() {
            return formatDate(lastAirDate)
        }

}