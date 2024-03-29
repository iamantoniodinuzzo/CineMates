package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.tvShow.GuestStarDTO
import com.example.cinemates.domain.model.tv.GuestStar


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */

fun GuestStarDTO.mapToGuestStar(): GuestStar {
    return GuestStar(
        id = this.id,
        name = this.name,
        profilePath = this.profilePath?:"",
        character = this.character,
    )
}