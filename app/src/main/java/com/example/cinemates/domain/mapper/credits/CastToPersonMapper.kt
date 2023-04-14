package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.data.remote.response.credits.PersonDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Person


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CastToPersonMapper : Mapper<PersonDTO, Person> {
    override fun map(input: PersonDTO): Person {
        return Person(
            id = input.id,
            name = input.name,
            profilePath = input.profilePath.toString()
        )
    }

}