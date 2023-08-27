package com.indisparte.tv.mapper

import com.indisparte.person.Cast
import com.indisparte.person.Crew
import com.indisparte.common.Genre
import com.indisparte.tv.Group
import com.indisparte.common.Network
import com.indisparte.common.ProductionCompany
import com.indisparte.common.ProductionCountry
import com.indisparte.common.SpokenLanguage
import com.indisparte.tv.TvShow
import com.indisparte.tv.TvShowDetails
import com.indisparte.common.Video
import com.indisparte.response.GenreDTO
import com.indisparte.response.ProductionCompanyDTO
import com.indisparte.response.ProductionCountryDTO
import com.indisparte.response.SpokenLanguageDTO
import com.indisparte.response.VideoDTO
import com.indisparte.tv.response.CastDTO
import com.indisparte.tv.response.CrewDTO
import com.indisparte.tv.response.GroupDTO
import com.indisparte.tv.response.NetworkDTO
import com.indisparte.tv.response.TvShowDTO
import com.indisparte.tv.response.TvShowDetailsDTO

/**
 * @author Antonio Di Nuzzo
 */
fun TvShowDTO.mapToTvShow(): com.indisparte.tv.TvShow {
    return com.indisparte.tv.TvShow(
        id = this.id,
        name = this.name,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage
    )
}

fun TvShowDetailsDTO.mapToTvShowDetails(): com.indisparte.tv.TvShowDetails {
    return com.indisparte.tv.TvShowDetails(
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



fun NetworkDTO.mapToNetwork(): com.indisparte.common.Network {
    return com.indisparte.common.Network(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name,
        originCountry = this.originCountry
    )
}

fun GenreDTO.mapToGenre(): com.indisparte.common.Genre {
    return com.indisparte.common.Genre(id = this.id, name = this.name)
}

fun ProductionCompanyDTO.mapToProductionCompany(): com.indisparte.common.ProductionCompany {
    return com.indisparte.common.ProductionCompany(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name
    )
}

fun ProductionCountryDTO.mapToProductionCountry(): com.indisparte.common.ProductionCountry {
    return com.indisparte.common.ProductionCountry(name = this.name)
}

fun SpokenLanguageDTO.mapToSpokenLanguage(): com.indisparte.common.SpokenLanguage {
    return com.indisparte.common.SpokenLanguage(englishName = this.englishName, name = this.name)
}

fun GroupDTO.mapToGroup(): com.indisparte.tv.Group {
    return com.indisparte.tv.Group(
        episodes = this.episodes.map { it.mapToEpisode() },
        id = this.id,
        locked = this.locked,
        name = this.name,
        order = this.order
    )
}

fun CastDTO.mapToCast(): com.indisparte.person.Cast {
    return com.indisparte.person.Cast(
        adult = this.adult,
        castId = this.id,
        character = this.roles.firstOrNull()?.character.orEmpty(),
        creditId = this.roles.firstOrNull()?.creditId.orEmpty(),
        gender = this.gender,
        id = this.id,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        order = this.order,
        originalName = this.originalName,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}
fun CrewDTO.mapToCrew(): com.indisparte.person.Crew {
    return com.indisparte.person.Crew(
        adult = this.adult,
        creditId = this.jobs.firstOrNull()?.creditId.orEmpty(),
        department = this.department,
        gender = this.gender,
        id = this.id,
        job = this.jobs.firstOrNull()?.job.orEmpty(),
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        originalName = this.originalName,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun VideoDTO.mapToVideo(): com.indisparte.common.Video {
    return com.indisparte.common.Video(
        id = this.id,
        key = this.key,
        name = this.name,
        official = this.official,
        site = this.site,
        type = this.type
    )
}




