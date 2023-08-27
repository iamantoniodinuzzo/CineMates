package com.indisparte.movie_data.mapper

import com.indisparte.movie_data.response.BelongsToCollectionDTO
import com.indisparte.movie_data.response.CastDTO
import com.indisparte.movie_data.response.CollectionDetailsResponseDTO
import com.indisparte.movie_data.response.CrewDTO
import com.indisparte.response.MovieDTO
import com.indisparte.movie_data.response.MovieDetailsDTO
import com.indisparte.movie_data.response.ReleaseDateDTO
import com.indisparte.movie_data.response.ReleaseDatesByCountryDTO
import com.indisparte.response.BackdropDTO
import com.indisparte.response.GenreDTO
import com.indisparte.response.ProductionCompanyDTO
import com.indisparte.response.ProductionCountryDTO
import com.indisparte.response.SpokenLanguageDTO
import com.indisparte.response.VideoDTO


/**
 * @author Antonio Di Nuzzo
 */
fun MovieDTO.toMovie(): com.indisparte.movie_data.Movie {
    return com.indisparte.movie_data.Movie(
        adult = this.adult,
        posterPath = this.posterPath,
        id = this.id,
        popularity = this.popularity,
        releaseDate = this.formattedReleaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}

fun MovieDetailsDTO.toMovieDetails(): com.indisparte.movie_data.MovieDetails {
    return com.indisparte.movie_data.MovieDetails(
        adult = this.adult, backdropPath = this.backdropPath,
        belongsToCollection = this.belongsToCollection?.mapToBelongToCollection(),
        budget = this.budget,
        genres = this.genres.map { it.mapToGenre() },
        homepage = this.homepage,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { it.mapToProductionCompany() },
        productionCountries = this.productionCountries.map { it.mapToProductionCountry() },
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = this.spokenLanguages.map { it.mapToSpokenLanguage() },
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun BelongsToCollectionDTO.mapToBelongToCollection(): com.indisparte.movie_data.BelongsToCollection {
    return com.indisparte.movie_data.BelongsToCollection(
        backdropPath = this.backdropPath,
        id = this.id,
        name = this.name,
        posterPath = this.posterPath
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

fun CastDTO.mapToCast(): com.indisparte.person.Cast {
    return com.indisparte.person.Cast(
        adult = this.adult,
        castId = this.castId,
        character = this.character,
        creditId = this.creditId,
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
        creditId = this.creditId,
        department = this.department,
        gender = this.gender,
        id = this.id,
        job = this.job,
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

fun ReleaseDateDTO.mapToReleaseDate(): com.indisparte.movie_data.ReleaseDate {
    return com.indisparte.movie_data.ReleaseDate(
        certification = this.certification,
        releaseDate = this.releaseDate,
        type = this.type
    )
}

fun ReleaseDatesByCountryDTO.mapToReleaseDatesByCountry(): com.indisparte.movie_data.ReleaseDatesByCountry {
    return com.indisparte.movie_data.ReleaseDatesByCountry(
        country = this.iso31661,
        releaseDates = this.releaseDates.map { it.mapToReleaseDate() }
    )
}

fun BackdropDTO.mapToBackdrop(): com.indisparte.common.Backdrop {
    return com.indisparte.common.Backdrop(
        filePath = this.filePath,
        height = this.height,
        width = this.width
    )
}

fun CollectionDetailsResponseDTO.mapToCollectionDetails(): com.indisparte.movie_data.CollectionDetails {
    return com.indisparte.movie_data.CollectionDetails(
        backdropPath = this.backdropPath,
        id = this.id,
        name = this.name,
        overview = this.overview,
        parts = this.parts.map { it.toMovie() },
        posterPath = this.posterPath
    )
}




