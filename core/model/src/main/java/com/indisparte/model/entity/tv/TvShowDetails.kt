package com.indisparte.model.entity.tv

import com.indisparte.model.entity.common.Genre
import com.indisparte.model.entity.common.Network
import com.indisparte.model.entity.common.ProductionCompany
import com.indisparte.model.entity.common.ProductionCountry
import com.indisparte.model.entity.common.SpokenLanguage


class TvShowDetails(
    val adult: Boolean,
    val backdropPath: String?,
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val lastEpisodeToAir: Episode,
    name: String,
    val networks: List<Network>,
    val nextEpisodeToAir: Episode?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    popularity: Double,
    posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    voteAverage: Double,
    val voteCount: Int
): TvShow(id = id, name = name, popularity = popularity, posterPath = posterPath, voteAverage = voteAverage)