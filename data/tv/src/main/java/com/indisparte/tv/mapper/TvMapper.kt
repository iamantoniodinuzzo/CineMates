package com.indisparte.tv.mapper

import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.CreatedBy
import com.indisparte.model.entity.Crew
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
import com.indisparte.tv.response.CastDTO
import com.indisparte.tv.response.CreatedByDTO
import com.indisparte.tv.response.CrewDTO
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



fun NetworkDTO.mapToNetwork(): Network {
    return Network(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name,
        originCountry = this.originCountry
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

fun CastDTO.mapToCast(): Cast {
    return Cast(
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
fun CrewDTO.mapToCrew(): Crew {
    return Crew(
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


