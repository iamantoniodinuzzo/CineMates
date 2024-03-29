package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.NetworkDTO
import com.example.cinemates.domain.model.tv.Network


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun NetworkDTO.mapToNetwork(): Network {
    return Network(
        id = this.id,
        logoPath = this.logoPath?:"",
        name = this.name
    )
}