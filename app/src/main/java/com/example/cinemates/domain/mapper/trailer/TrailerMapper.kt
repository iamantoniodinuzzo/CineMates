package com.example.cinemates.domain.mapper.trailer

import com.example.cinemates.data.remote.response.trailer.VideoDTO
import com.example.cinemates.domain.model.Video


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */

fun VideoDTO.mapToVideo():Video{
    return Video(
        id = this.id,
        key = this.key,
        type = this.type,
        name = this.name,
        site = this.site,
        isOfficial = this.isOfficial
    )
}