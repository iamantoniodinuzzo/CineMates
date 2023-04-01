package com.example.cinemates.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class TvShow(
    backdropPath: String?,
    @SerializedName("created_by")
    val createdBy: List<CreatedBy>,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    private val firstAirDate: String,
    genres: List<Genre>,
    homepage: String,
    id: Int,
    @SerializedName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerializedName("last_air_date")
    private val lastAirDate: String,
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
    originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    overview: String,
    popularity: Double,
    posterPath: String?,
    productionCompanies: List<ProductionCompany>,
    productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    status: String,
    tagline: String,
    val type: String,
    voteAverage: Double,
    personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    favorite: Boolean = false,
    voteCount: Int
) : Media(
    id,
    name,
    posterPath,
    backdropPath,
    voteAverage,
    originalName,
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


    val formattedFirstAirDate: String
        get() {
            return formatDate(firstAirDate)
        }

    val formattedLastAirDate: String
        get() {
            return formatDate(lastAirDate)
        }

    override fun toString(): String {
        return "TvShow(backdropPath=$backdropPath, createdBy=$createdBy, episodeRunTime=$episodeRunTime, firstAirDate='$firstAirDate', genres=$genres, homepage='$homepage', id=$id, inProduction=$inProduction, languages=$languages, lastAirDate='$lastAirDate', lastEpisodeToAir=$lastEpisodeToAir, name='$name', networks=$networks, nextEpisodeToAir=$nextEpisodeToAir, numberOfEpisodes=$numberOfEpisodes, numberOfSeasons=$numberOfSeasons, originCountry=$originCountry, originalLanguage='$originalLanguage', originalName='$originalName', overview='$overview', popularity=$popularity, posterPath=$posterPath, productionCompanies=$productionCompanies, productionCountries=$productionCountries, seasons=$seasons, spokenLanguages=$spokenLanguages, status='$status', tagline='$tagline', type='$type', voteAverage=$voteAverage, voteCount=$voteCount)"
    }

}