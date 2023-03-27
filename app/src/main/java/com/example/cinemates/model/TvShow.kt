package com.example.cinemates.model

import com.example.cinemates.util.MediaType


class TvShow(
    backdrop_path: String,
    val created_by: List<CreatedBy>? = listOf(),
    val episode_run_time: List<Int>? = listOf(),
    val first_air_date: String? = null,
    genres: List<Genre>,
    homepage: String,
    id: Int,
    val in_production: Boolean? = null,
    val languages: List<String>? = listOf(),
    val last_air_date: String? = null,
    val last_episode_to_air: LastEpisodeToAir? = null,
    val name: String,
    val networks: List<Network>? = listOf(),
    val next_episode_to_air: Any? = null,
    val number_of_episodes: Int? = null,
    val number_of_seasons: Int? = null,
    val origin_country: List<String>? = listOf(),
    original_language: String,
    val original_name: String? = null,
    overview: String,
    popularity: Double,
    poster_path: String,
     production_companies: List<ProductionCompany>,
     production_countries: List<ProductionCountry>,
    val seasons: List<Season>? = listOf(),
    val spoken_languages: List<SpokenLanguage>? = listOf(),
    status: String,
    tagline: String,
    val type: String? = null,
    vote_average: Double,
    vote_count: Int,
    personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    favorite: Boolean = false,
) : Media(
    backdrop_path,
    genres,
    id,
    original_language,
    poster_path,
    popularity,
    vote_average,
    vote_count,
    name,
    personalStatus,
    favorite,
    tagline,
    status,
    overview,
    homepage,
    production_companies,
    production_countries
) {
    override fun getType(): MediaType {
        return MediaType.MOVIE
    }


}