package com.indisparte.actor.mapper

import com.indisparte.actor.response.KnownForDTO
import com.indisparte.actor.response.PersonDTO
import com.indisparte.actor.response.PersonDetailsDTO
import com.indisparte.model.entity.KnownFor
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.PersonDetails

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun PersonDTO.mapToPerson(): Person {
    return Person(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        knownFor = this.knownFor.map { it.mapToKnownFor() },
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun PersonDetailsDTO.mapToPersonDetails(): PersonDetails {
    return PersonDetails(
        adult = this.adult,
        alsoKnownAs = this.alsoKnownAs,
        biography = this.biography,
        birthday = this.birthday,
        `death-day` = this.deathDay,
        gender = this.gender,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        placeOfBirth = this.placeOfBirth,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun KnownForDTO.mapToKnownFor(): KnownFor {
    return KnownFor(
        adult = this.adult,
        backdropPath = this.backdropPath,
        firstAirDate = this.firstAirDate,
        genreIds = this.genreIds,
        id = this.id,
        mediaType = this.mediaType,
        name = this.name,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        originalTitle = this.originalTitle,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

