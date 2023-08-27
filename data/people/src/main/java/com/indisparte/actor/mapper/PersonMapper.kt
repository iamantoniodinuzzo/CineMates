package com.indisparte.actor.mapper

import com.indisparte.actor.response.KnownForDTO
import com.indisparte.actor.response.PersonDTO
import com.indisparte.actor.response.PersonDetailsDTO
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



