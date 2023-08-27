package com.indisparte.model.entity.tv

import com.indisparte.model.entity.common.Genre
import com.indisparte.model.entity.common.Network
import com.indisparte.model.entity.common.ProductionCompany
import com.indisparte.model.entity.common.ProductionCountry
import com.indisparte.model.entity.common.SpokenLanguage
import com.indisparte.model.util.Constants


class TvShowDetails(
    val adult: Boolean,
    private val backdropPath: String?,
    val createdBy: List<CreatedBy>,
    private val episodeRunTime: List<Int>,
    private val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    private val lastAirDate: String,
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
    val voteCount: Int,
) : TvShow(
    id,
    name,
    popularity,
    posterPath,
    voteAverage
) {
    val completeBackdropPathW780: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W780, backdropPath)
    val completeBackdropPathW500: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W500, backdropPath)

    val formattedListEpisodeRuntime: List<String>
        get() = episodeRunTime.map { formatRuntime(it) }

    val formattedAirDate: String?
        get() = formatDate(firstAirDate)
    val formattedLastAirDate: String?
        get() = formatDate(lastAirDate)

}