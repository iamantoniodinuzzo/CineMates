package com.example.cinemates.domain.mapper

import com.example.cinemates.data.remote.response.common.GenreDTO
import com.example.cinemates.domain.model.Genre


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun GenreDTO.mapToGenre():Genre{
    return Genre(
        id = this.id,
        name =  this.name
    )
}