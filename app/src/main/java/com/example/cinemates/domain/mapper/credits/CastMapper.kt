package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.domain.model.Cast


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */

fun CastDTO.mapToCast():Cast{
    return Cast(
        id = this.id,
        name = this.name,
        profilePath = this.profilePath?:"",
        character = this.character,
    )
}