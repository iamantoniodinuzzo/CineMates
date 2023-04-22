package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.CreatedByDTO
import com.example.cinemates.domain.model.Crew


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun CreatedByDTO.toCrew(): Crew {
    return Crew(
        id = this.id,
        name = this.name,
        profilePath = this.profilePath?:"",
        job = ""
    )
}