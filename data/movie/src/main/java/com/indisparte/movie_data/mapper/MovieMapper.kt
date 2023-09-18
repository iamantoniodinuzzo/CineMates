package com.indisparte.movie_data.mapper

import com.indisparte.common.Backdrop
import com.indisparte.common.Genre
import com.indisparte.common.MediaType
import com.indisparte.common.ProductionCompany
import com.indisparte.common.ProductionCountry
import com.indisparte.common.SpokenLanguage
import com.indisparte.common.Video
import com.indisparte.movie_data.BelongsToCollection
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDate
import com.indisparte.movie_data.ReleaseDatesByCountry
import com.indisparte.movie_data.response.BelongsToCollectionDTO
import com.indisparte.movie_data.response.CastDTO
import com.indisparte.movie_data.response.CollectionDetailsResponseDTO
import com.indisparte.movie_data.response.CrewDTO
import com.indisparte.movie_data.response.MovieDetailsDTO
import com.indisparte.movie_data.response.ReleaseDateDTO
import com.indisparte.movie_data.response.ReleaseDatesByCountryDTO
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import com.indisparte.response.BackdropDTO
import com.indisparte.response.GenreDTO
import com.indisparte.response.MovieDTO
import com.indisparte.response.ProductionCompanyDTO
import com.indisparte.response.ProductionCountryDTO
import com.indisparte.response.SpokenLanguageDTO
import com.indisparte.response.VideoDTO

/**
 * @author Antonio Di Nuzzo
 */
fun MovieDTO.toMovie(): Movie {
    return Movie(
        adult = this.adult,
        posterPath = this.posterPath,
        id = this.id,
        popularity = this.popularity,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}

fun MovieDetailsDTO.toMovieDetails(): MovieDetails {
    return MovieDetails(
        adult = this.adult, backdropPath = this.backdropPath,
        belongsToCollection = this.belongsToCollection?.mapToBelongToCollection(),
        budget = this.budget,
        genres = this.genres.map { it.mapToGenre(MediaType.MOVIE) },
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

fun BelongsToCollectionDTO.mapToBelongToCollection(): BelongsToCollection {
    return BelongsToCollection(
        backdropPath = this.backdropPath,
        id = this.id,
        name = this.name,
        posterPath = this.posterPath
    )
}

fun GenreDTO.mapToGenre(mediaType: MediaType): Genre {
    return Genre(id = this.id, name = this.name, mediaType = mediaType)
}

fun ProductionCompanyDTO.mapToProductionCompany(): ProductionCompany {
    return ProductionCompany(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name
    )
}

fun ProductionCountryDTO.mapToProductionCountry(): ProductionCountry {
    return ProductionCountry(name = this.name)
}

fun SpokenLanguageDTO.mapToSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(englishName = this.englishName, name = this.name)
}

fun CastDTO.mapToCast(): Cast {
    return Cast(
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

fun CrewDTO.mapToCrew(): Crew {
    return Crew(
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

fun VideoDTO.mapToVideo(): Video {
    return Video(
        id = this.id,
        key = this.key,
        name = this.name,
        official = this.official,
        site = this.site,
        type = this.type
    )
}

fun ReleaseDateDTO.mapToReleaseDate(): ReleaseDate {
    return ReleaseDate(
        certification = this.certification,
        releaseDate = this.releaseDate,
        type = this.type
    )
}

fun ReleaseDatesByCountryDTO.mapToReleaseDatesByCountry(): ReleaseDatesByCountry {
    return ReleaseDatesByCountry(
        country = this.iso31661,
        releaseDates = this.releaseDates.map { it.mapToReleaseDate() }
    )
}

fun BackdropDTO.mapToBackdrop(): Backdrop {
    return Backdrop(
        filePath = this.filePath,
        height = this.height,
        width = this.width
    )
}

fun CollectionDetailsResponseDTO.mapToCollectionDetails(): CollectionDetails {
    return CollectionDetails(
        backdropPath = this.backdropPath,
        id = this.id,
        name = this.name,
        overview = this.overview,
        parts = this.parts.map { it.toMovie() },
        posterPath = this.posterPath
    )
}




