package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.credits.PersonDTO
import com.example.cinemates.domain.model.credits.PersonDetails


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun PersonDTO.mapToPersonDetails(): PersonDetails {
    return PersonDetails(
        adult = this.adult,
        alsoKnownAs = this.alsoKnownAs,
        biography = this.biography,
        birthday = this.formattedBirthday,
        deathDay = this.formattedDeathDay,
        gender = this.formattedGender,
        homepage = this.homepage.toString(),
        id = this.id,
        imdbId = this.imdbId?:"",
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        placeOfBirth = this.placeOfBirth ?: "",
        popularity = this.popularity,
        profilePath = this.profilePath?:"",
        age = this.age
    )
}