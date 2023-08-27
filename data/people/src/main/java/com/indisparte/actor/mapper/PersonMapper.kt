package com.indisparte.actor.mapper

import com.indisparte.actor.response.KnownForDTO
import com.indisparte.actor.response.PersonDTO
import com.indisparte.actor.response.PersonDetailsDTO
import com.indisparte.model.entity.person.KnownFor
import com.indisparte.model.entity.person.Person
import com.indisparte.model.entity.person.PersonDetails
import com.indisparte.model.entity.common.Poster
import com.indisparte.response.PosterDTO

/**
 * @author Antonio Di Nuzzo
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
        deathDay = this.deathDay,
        gender = this.gender,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        placeOfBirth = this.placeOfBirth,
        popularity = this.popularity,
        profilePath = this.profilePath,
        images = this.images.profiles.map {
            it.mapToPoster()
        }
    )
}

fun PosterDTO.mapToPoster(): Poster {
    return Poster(
        aspectRatio = aspectRatio,
        filePath = filePath
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

