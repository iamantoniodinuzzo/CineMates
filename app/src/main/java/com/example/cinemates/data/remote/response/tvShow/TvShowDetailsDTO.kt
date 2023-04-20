package com.example.cinemates.data.remote.response.tvShow


import com.example.cinemates.data.remote.response.common.GenreDTO
import com.example.cinemates.data.remote.response.common.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.common.ProductionCountryDTO
import com.example.cinemates.data.remote.response.common.SpokenLanguageDTO
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

class TvShowDetailsDTO(
    backdropPath: String?,
    @SerializedName("created_by")
    val createdBy: List<CreatedByDTO>,
    @SerializedName("episode_run_time")
    private val episodeRunTime: List<Int>,
    firstAirDate: String ,
    genres: List<GenreDTO> ,
    val homepage: String ,
    id: Int ,
    @SerializedName("in_production")
    val inProduction: Boolean ,
    val languages: List<String> ,
    @SerializedName("last_air_date")
    val lastAirDate: String ,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeDTO? ,
    name: String ,
    val networks: List<NetworkDTO>,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeDTO?,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    originCountry: List<String>,
    originalLanguage: String ,
    originalName: String,
    overview: String,
    popularity: Double,
    posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDTO>,
    val seasons: List<SeasonDTO> ,
    @SerializedName("spoken_languages")
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