package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.credits.PersonDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.PersonDetails


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*
class PersonDetailsMapper : Mapper<PersonDTO, PersonDetails> {
    override fun map(input: PersonDTO): PersonDetails {
        return PersonDetails(
            adult = input.adult,
            alsoKnownAs = input.alsoKnownAs,
            biography = input.biography,
            birthday = input.formattedBirthday,
            deathDay = input.formattedDeathDay,
            gender = input.formattedGender,
            homepage = input.homepage.toString(),
            id = input.id,
            imdbId = input.imdbId,
            knownForDepartment = input.knownForDepartment,
            name = input.name,
            placeOfBirth = input.placeOfBirth,
            popularity = input.popularity,
            profilePath = input.profilePath,
            age = input.age
        )
    }

}
*/

fun PersonDTO.mapToPersonDetails():PersonDetails{
    return PersonDetails(
        adult = this.adult,
        alsoKnownAs = this.alsoKnownAs,
        biography = this.biography,
        birthday = this.formattedBirthday,
        deathDay = this.formattedDeathDay,
        gender = this.formattedGender,
        homepage = this.homepage.toString(),
        id = this.id,
        imdbId = this.imdbId,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        placeOfBirth = this.placeOfBirth,
        popularity = this.popularity,
        profilePath = this.profilePath,
        age = this.age
    )
}