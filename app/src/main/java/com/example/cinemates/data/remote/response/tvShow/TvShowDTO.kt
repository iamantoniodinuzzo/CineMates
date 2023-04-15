package com.example.cinemates.data.remote.response.tvShow


import com.example.cinemates.data.remote.response.GenreDTO
import com.example.cinemates.data.remote.response.ProductionCompanyDTO
import com.example.cinemates.data.remote.response.ProductionCountryDTO
import com.example.cinemates.data.remote.response.SpokenLanguageDTO
import com.example.cinemates.domain.model.Season
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowDTO(
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("created_by")
    val createdBy: List<CreatedByDTO> = listOf(),
    @SerialName("episode_run_time")
    val episodeRunTime: List<Int> = listOf(),
    @SerialName("first_air_date")
    val firstAirDate: String = "",
    val genres: List<GenreDTO> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    @SerialName("in_production")
    val inProduction: Boolean = false,
    val languages: List<String> = listOf(),
    @SerialName("last_air_date")
    val lastAirDate: String = "",
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeDTO? = null,
    val name: String = "",
    val networks: List<NetworkDTO> = listOf(),
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: Any? = null,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int = 0,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int = 0,
    @SerialName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_name")
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO> = listOf(),
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDTO> = listOf(),
    val seasons: List<Season> = listOf(),
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDTO> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val type: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)