package com.indisparte.actor.mapper

import com.indisparte.actor.response.PersonDTO
import com.indisparte.actor.response.PersonDetailsDTO
import com.indisparte.person.Person
import com.indisparte.person.PersonDetails
import com.indisparte.common.Poster
import com.indisparte.response.PosterDTO

/**
 * @author Antonio Di Nuzzo
 */
fun PersonDTO.mapToPerson(): com.indisparte.person.Person {
    return com.indisparte.person.Person(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun PersonDetailsDTO.mapToPersonDetails(): com.indisparte.person.PersonDetails {
    return com.indisparte.person.PersonDetails(
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

fun PosterDTO.mapToPoster(): com.indisparte.common.Poster {
    return com.indisparte.common.Poster(
        aspectRatio = aspectRatio,
        filePath = filePath
    )
}



