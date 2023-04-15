package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.credits.CrewDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Crew


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CrewMapper : Mapper<CrewDTO, Crew> {
    override fun map(input: CrewDTO): Crew {
        return Crew(
            id = input.id,
            name = input.name,
            profilePath = input.profilePath.toString(),
            job = input.job
        )
    }

}