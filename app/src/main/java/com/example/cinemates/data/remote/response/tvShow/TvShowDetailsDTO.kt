package com.example.cinemates.data.remote.response.tvShow


import com.example.cinemates.data.remote.response.common.GenreDTO
import com.example.cinemates.data.remote.response.common.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.common.ProductionCountryDTO
import com.example.cinemates.data.remote.response.common.SpokenLanguageDTO
import kotlinx.serialization.SerialName

class TvShowDetailsDTO(
    backdropPath: String = "",
    @SerialName("created_by")
    val createdBy: List<CreatedByDTO> = listOf(),
    @SerialName("episode_run_time")
    private val episodeRunTime: List<Int> = listOf(),
    firstAirDate: String = "",
    genres: List<GenreDTO> = listOf(),
    val homepage: String = "",
    id: Int = 0,
    @SerialName("in_production")
    val inProduction: Boolean = false,
    val languages: List<String> = listOf(),
    @SerialName("last_air_date")
    val lastAirDate: String = "",
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeDTO? = null,
    name: String = "",
    val networks: List<NetworkDTO> = listOf(),
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeDTO? = null,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int = 0,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int = 0,
    originCountry: List<String> = listOf(),
    originalLanguage: String = "",
    originalName: String = "",
    overview: String = "",
    popularity: Double = 0.0,
    posterPath: String = "",
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO> = listOf(),
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDTO> = listOf(),
    val seasons: List<SeasonDTO> = listOf(),
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDTO> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val type: String = "",
    voteAverage: Double = 0.0,
    voteCount: Int = 0
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