package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.GroupDTO
import com.example.cinemates.domain.model.tv.Group


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun GroupDTO.mapToGroup(): Group {
    return Group(
        episodes = this.episodes.map {
            it.mapToEpisode()
        },
        id = this.id,
        locked = this.locked,
        name = this.name,
        order = this.order,
    )
}