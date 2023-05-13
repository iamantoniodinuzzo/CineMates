package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.TvShowDTO
import com.example.cinemates.domain.model.common.Media
import com.example.cinemates.util.MediaType


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun TvShowDTO.mapToMedia(): Media {
    return Media(
        mediaType = MediaType.TV,
        id = this.id,
        title = this.name,
        backdropPath = this.backdropPath ?: "",
        posterPath = this.posterPath ?: "",
        voteAverage = this.voteAverage
    )
}