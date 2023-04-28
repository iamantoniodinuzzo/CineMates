package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.credits.PersonDTO
import com.example.cinemates.domain.model.credits.Person


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun PersonDTO.mapToPerson(): Person {
    return Person(
        id = this.id,
        name = this.name,
        profilePath = this.profilePath?:"",
    )
}