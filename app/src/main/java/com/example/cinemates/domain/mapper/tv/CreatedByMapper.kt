package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.CreatedByDTO
import com.example.cinemates.domain.model.CreatedBy


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun CreatedByDTO.mapToCreatedBy():CreatedBy{
    return CreatedBy(
        creditId = this.creditId,
        gender = formattedGender,
        id = this.id,
        name = this.name,
        profilePath = this.profilePath?:""
    )
}