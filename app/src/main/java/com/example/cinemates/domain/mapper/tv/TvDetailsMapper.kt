package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.TvShowDTO
import com.example.cinemates.data.remote.response.tvShow.TvShowDetailsDTO
import com.example.cinemates.domain.mapper.*
import com.example.cinemates.domain.model.TvShow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun TvShowDetailsDTO.mapToTvShow():TvShow{
    return TvShow(
        backdropPath = this.backdropPath?:"",
        createdBy = this.createdBy.map { it.mapToCreatedBy()},
        episodeRunTime = this.formattedEpisodesRuntime,
        firstAirDate = this.formattedFirstAirDate,
        genres = this.genres.map { it.mapToGenre() },
        homepage = this.homepage,
        id = this.id,
        inProduction = this.inProduction,
        languages = this.languages,
        lastAirDate = this.lastAirDate,
        lastEpisodeToAir = this.lastEpisodeToAir?.mapToEpisode(),
        name = this.name,
        network = this.networks.map { it.mapToNetwork() },
        nextEpisodeToAir = this.nextEpisodeToAir?.mapToEpisode(),
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath?:"",
        productionCompanies = this.productionCompanies.map { it.mapToProductionCompany() },
        productionCountries = this.productionCountries.map { it.mapToProductionCountry() },
        seasons = this.seasons.map { it.mapToSeason() },
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        voteAverage = this.voteAverage
    )
}