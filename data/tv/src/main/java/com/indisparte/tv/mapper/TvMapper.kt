package com.indisparte.tv.mapper

import com.indisparte.model.entity.CreatedBy
import com.indisparte.model.entity.Episode
import com.indisparte.model.entity.EpisodeGroup
import com.indisparte.model.entity.EpisodeGroupDetails
import com.indisparte.model.entity.Genre
import com.indisparte.model.entity.Group
import com.indisparte.model.entity.Network
import com.indisparte.model.entity.ProductionCompany
import com.indisparte.model.entity.ProductionCountry
import com.indisparte.model.entity.Season
import com.indisparte.model.entity.SeasonDetails
import com.indisparte.model.entity.SpokenLanguage
import com.indisparte.model.entity.TvShow
import com.indisparte.model.entity.TvShowDetails
import com.indisparte.response.GenreDTO
import com.indisparte.response.ProductionCompanyDTO
import com.indisparte.response.ProductionCountryDTO
import com.indisparte.response.SpokenLanguageDTO
import com.indisparte.tv.response.CreatedByDTO
import com.indisparte.tv.response.EpisodeDTO
import com.indisparte.tv.response.EpisodeGroupDTO
import com.indisparte.tv.response.EpisodeGroupDetailsDTO
import com.indisparte.tv.response.GroupDTO
import com.indisparte.tv.response.NetworkDTO
import com.indisparte.tv.response.SeasonDTO
import com.indisparte.tv.response.SeasonDetailsDTO
import com.indisparte.tv.response.TvShowDTO
import com.indisparte.tv.response.TvShowDetailsDTO

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun TvShowDTO.mapToTvShow(): TvShow {
    return TvShow(
        id = this.id,
        name = this.name,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage
    )
}

fun TvShowDetailsDTO.mapToTvShowDetails(): TvShowDetails {
    return TvShowDetails(
        adult = this.adult,
        backdropPath = this.backdropPath,
        createdBy = this.createdBy.map { it.mapToCreatedBy() },
        episodeRunTime = this.episodeRunTime,
        firstAirDate = this.firstAirDate,
        genres = this.genres.map { it.mapToGenre() },
        homepage = this.homepage,
        id = this.id,
        inProduction = this.inProduction,
        languages = this.languages,
        lastAirDate = this.lastAirDate,
        lastEpisodeToAir = this.lastEpisodeToAir.mapToEpisode(),
        name = this.name,
        networks = this.networks.map { it.mapToNetwork() },
        nextEpisodeToAir = this.nextEpisodeToAir?.mapToEpisode(),
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { it.mapToProductionCompany() },
        productionCountries = this.productionCountries.map { it.mapToProductionCountry() },
        seasons = this.seasons.map { it.mapToSeason() },
        spokenLanguages = this.spokenLanguages.map { it.mapToSpokenLanguage() },
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun SeasonDTO.mapToSeason(): Season {
    return Season(
        airDate = this.airDate,
        episodeCount = this.episodeCount,
        id = this.id,
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath,
        seasonNumber = this.seasonNumber,
        voteAverage = this.voteAverage
    )
}

fun SeasonDetailsDTO.mapToSeasonDetails(): SeasonDetails {
    return SeasonDetails(
        airDate = this.airDate,
        episodes = this.episodes.map { it.mapToEpisode() },
        id = this.id,
        seasonId = this.seasonId,
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath,
        seasonNumber = this.seasonNumber,
        voteAverage = this.voteAverage
    )
}

fun NetworkDTO.mapToNetwork(): Network {
    return Network(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name,
        originCountry = this.originCountry
    )
}

fun EpisodeGroupDTO.mapToEpisodeGroup(): EpisodeGroup {
    return EpisodeGroup(
        description = this.description,
        episodeCount = this.episodeCount,
        groupCount = this.groupCount,
        id = this.id,
        name = this.name,
        network = this.network,
        type = this.type
    )
}

fun EpisodeGroupDetailsDTO.mapToEpisodeGroupDetails(): EpisodeGroupDetails {
    return EpisodeGroupDetails(
        description = this.description,
        episodeCount = this.episodeCount,
        groupCount = this.groupCount,
        groups = this.groups.map { it.mapToGroup() },
        id = this.id,
        name = this.name,
        network = this.network?.mapToNetwork(),
        type = this.type
    )
}

fun EpisodeDTO.mapToEpisode(): Episode {
    return Episode(
        airDate = this.airDate,
        episodeNumber = this.episodeNumber,
        id = this.id,
        name = this.name,
        overview = this.overview,
        runtime = this.runtime,
        seasonNumber = this.seasonNumber,
        showId = this.showId,
        stillPath = this.stillPath,
        voteAverage = this.voteAverage,
    )
}

fun CreatedByDTO.mapToCreatedBy(): CreatedBy {
    return CreatedBy(
        creditId = this.creditId,
        gender = this.gender,
        id = this.id,
        name = this.name,
        profilePath = this.profilePath
    )
}

fun GenreDTO.mapToGenre(): Genre {
    return Genre(id = this.id, name = this.name)
}

fun ProductionCompanyDTO.mapToProductionCompany(): ProductionCompany {
    return ProductionCompany(id = this.id, logoPath = this.logoPath, name = this.name)
}

fun ProductionCountryDTO.mapToProductionCountry(): ProductionCountry {
    return ProductionCountry(name = this.name)
}

fun SpokenLanguageDTO.mapToSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(englishName = this.englishName, name = this.name)
}

fun GroupDTO.mapToGroup(): Group {
    return Group(
        episodes = this.episodes.map { it.mapToEpisode() },
        id = this.id,
        locked = this.locked,
        name = this.name,
        order = this.order
    )
}


