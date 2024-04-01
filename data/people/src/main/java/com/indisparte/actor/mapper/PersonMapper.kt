package com.indisparte.actor.mapper

import com.indisparte.actor.response.PersonDTO
import com.indisparte.actor.response.PersonDetailsDTO
import com.indisparte.common.Poster
import com.indisparte.base.Person
import com.indisparte.database.entity.ActorEntity
import com.indisparte.person.Cast
import com.indisparte.person.PersonDetails
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

fun Person.asEntity():ActorEntity{
    return ActorEntity(actorId = this.id, name = this.name, posterPath = this.profilePath)
}





