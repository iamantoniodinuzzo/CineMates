package com.example.cinemates.domain.model

import com.example.cinemates.util.MediaType


class TvShow(
     backdropPath: String?,
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<String>,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val lastEpisodeToAir: Episode?,
    val name: String,
    val network: List<Network>,
    val nextEpisodeToAir: Episode?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
     posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    val status: String,
    val tagline: String,
    val type: String,
     voteAverage: Double,
) : Media(
    MediaType.TV,
    id,
    name,
    posterPath,
    backdropPath,
    voteAverage
)