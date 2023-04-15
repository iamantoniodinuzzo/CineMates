package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.PersonDetails


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PersonDetailsMapper : Mapper<PersonDTO, PersonDetails> {
    override fun map(input: PersonDTO): PersonDetails {
        val gender = if (input.gender == 1) {
            "Female"
        } else {
            "Male"
        }
        return PersonDetails(
            adult = input.adult,
            alsoKnownAs = input.alsoKnownAs,
            biography = input.biography.toString(),
            birthday = input.formattedBirthday,
            deathDay = input.formattedDeathday,
            gender = gender,
            homepage = input.homepage.toString(),
            id = input.id,
            imdbId = input.imdbId.toString(),
            knownForDepartment = input.knownForDepartment.toString(),
            name = input.name,
            placeOfBirth = input.placeOfBirth.toString(),
            popularity = input.popularity,
            profilePath = input.profilePath,
            age = input.age

        )
    }

}