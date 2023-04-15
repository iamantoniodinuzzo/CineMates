package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.CreatedByDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Crew
import com.example.cinemates.domain.model.Person


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CreatedByToPersonMapper:Mapper<CreatedByDTO, Crew> {
    override fun map(input: CreatedByDTO): Crew {
        return Crew(
            id = input.id ,
            name = input.name,
            profilePath = input.profilePath,
            job = ""
        )
    }

}