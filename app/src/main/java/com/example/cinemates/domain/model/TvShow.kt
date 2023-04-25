package com.example.cinemates.domain.model

import com.example.cinemates.util.MediaType


class TvShow(
    backdropPath: String,
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
    posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    val status: String,
    val tagline: String,
    val type: String,
    voteAverage: Double,
    val spokenLanguage : List<SpokenLanguage>
) : Media(
    MediaType.TV,
    id,
    name,
    posterPath,
    backdropPath,
    voteAverage
){
    override fun toString(): String {
        return "TvShow(createdBy=$createdBy, episodeRunTime=$episodeRunTime, firstAirDate='$firstAirDate', genres=$genres, homepage='$homepage', inProduction=$inProduction, languages=$languages, lastAirDate='$lastAirDate', lastEpisodeToAir=$lastEpisodeToAir, name='$name', network=$network, nextEpisodeToAir=$nextEpisodeToAir, numberOfEpisodes=$numberOfEpisodes, numberOfSeasons=$numberOfSeasons, originCountry=$originCountry, originalLanguage='$originalLanguage', originalName='$originalName', overview='$overview', popularity=$popularity, productionCompanies=$productionCompanies, productionCountries=$productionCountries, seasons=$seasons, status='$status', tagline='$tagline', type='$type')"
    }
}