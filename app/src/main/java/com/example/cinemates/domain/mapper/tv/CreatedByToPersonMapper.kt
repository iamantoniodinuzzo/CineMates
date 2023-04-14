package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.CreatedByDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Person


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CreatedByToPersonMapper:Mapper<CreatedByDTO, Person> {
    override fun map(input: CreatedByDTO): Person {
        return Person(
            id = input.id ,
            name = input.name,
            profilePath = input.profilePath.toString()
        )
    }

}