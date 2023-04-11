package com.example.cinemates.model

import com.example.cinemates.util.MediaType
import com.google.gson.annotations.Expose
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
    name: String,
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
    MediaType.TV,
    id,
    null,
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

   

}