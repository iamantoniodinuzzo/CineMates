package com.example.cinemates.data.remote.response.tvShow

import com.example.cinemates.data.remote.response.common.GenreDTO
import com.example.cinemates.data.remote.response.common.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.common.ProductionCountryDTO
import com.example.cinemates.data.remote.response.common.SpokenLanguageDTO
import com.example.cinemates.domain.model.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TvShowDetailsDTO(
    backdropPath: String,
    @SerialName("created_by")
    val createdBy: List<CreatedByDTO>,
    @SerialName("episode_run_time")
    private val episodeRunTime: List<Int>,
    @SerialName("first_air_date")
    val firstAirDate: String,
    genres: List<GenreDTO>,
    val homepage: String,
    id: Int,
    @SerialName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerialName("last_air_date")
    val lastAirDate: String,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeDTO,
    name: String,
    val networks: List<NetworkDTO>,
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeDTO?,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int,
    originCountry: List<String>,
    originalLanguage: String,
    originalName: String,
    overview: String,
    popularity: Double,
    posterPath: String,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDTO>,
    val seasons: List<SeasonDTO>,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDTO>,
    val status: String,
    val tagline: String,
    val type: String,
    voteAverage: Double,
    voteCount: Int
) : TvShowDTO(
    backdropPath,
    firstAirDate,
    genres,
    id,
    name,
    originCountry,
    originalLanguage,
    originalName,
    overview,
    popularity,
    posterPath,
    voteAverage,
    voteCount
) {
    private fun formatTimeMeasurement(timeMeasurement: Int): String {
        val hours = timeMeasurement / 60
        val minutes = timeMeasurement % 60
        return String.format("%02d h %02d min", hours, minutes)
    }

    val formattedEpisodesRuntime: List<String>
        get() {
            return episodeRunTime.map { formatTimeMeasurement(it) }
        }


}