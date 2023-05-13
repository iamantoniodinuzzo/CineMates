package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.credits.CrewDTO
import com.example.cinemates.domain.model.credits.Crew


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun CrewDTO.mapToCrew(): Crew {
    return Crew(
        id = this.id,
        name = this.name,
        profilePath = this.profilePath?:"",
        job = this.job
    )
}