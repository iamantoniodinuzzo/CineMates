package com.indisparte.movie.mapper

import com.indisparte.model.entity.BelongsToCollection
import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Genre
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.MovieDetails
import com.indisparte.model.entity.ProductionCompany
import com.indisparte.model.entity.ProductionCountry
import com.indisparte.model.entity.SpokenLanguage
import com.indisparte.movie.response.BelongsToCollectionDTO
import com.indisparte.response.CastDTO
import com.indisparte.response.CrewDTO
import com.indisparte.movie.response.GenreDTO
import com.indisparte.movie.response.MovieDTO
import com.indisparte.movie.response.MovieDetailsDTO


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun MovieDTO.toMovie(): Movie {
    return Movie(
        adult = this.adult,
        posterPath = this.posterPath,
        id = this.id,
        popularity = this.popularity,
        releaseDate = this.formattedReleaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}

fun MovieDetailsDTO.toMovieDetails(): MovieDetails {
    return MovieDetails(
        adult = this.adult, backdropPath = this.backdropPath,
        belongsToCollection = this.belongsToCollection.mapToBelongToCollection(),
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

fun BelongsToCollectionDTO.mapToBelongToCollection(): BelongsToCollection {
    return BelongsToCollection(
        backdropPath = this.backdropPath,
        id = this.id,
        name = this.name,
        posterPath = this.posterPath
    )
}

fun GenreDTO.mapToGenre(): Genre {
    return Genre(id = this.id, name = this.name)
}

fun com.indisparte.response.ProductionCompanyDTO.mapToProductionCompany(): ProductionCompany {
    return ProductionCompany(id = this.id, logoPath = this.logoPath, name = this.name)
}

fun com.indisparte.response.ProductionCountryDTO.mapToProductionCountry(): ProductionCountry {
    return ProductionCountry(name = this.name)
}

fun com.indisparte.response.SpokenLanguageDTO.mapToSpokenLanguage(): SpokenLanguage {
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


