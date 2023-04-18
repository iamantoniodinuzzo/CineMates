package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.NetworkDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Network


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class NetworkMapper : Mapper<NetworkDTO, Network> {
    override fun map(input: NetworkDTO): Network {
        return Network(
            id = input.id,
            logoPath = input.logoPath,
            name = input.name
        )
    }

}*/

fun NetworkDTO.mapToNetwork():Network{
    return Network(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name
    )
}