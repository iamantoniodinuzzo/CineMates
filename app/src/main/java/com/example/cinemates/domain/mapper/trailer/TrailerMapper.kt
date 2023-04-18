package com.example.cinemates.domain.mapper.trailer

import com.example.cinemates.data.remote.response.trailer.VideoDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Video


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*
class TrailerMapper : Mapper<VideoDTO, Video> {
    override fun map(input: VideoDTO): Video {
        return Video(
            id = input.id,
            key = input.key,
            type = input.type,
            name = input.name,
            site = input.site,
            isOfficial = input.isOfficial
        )
    }

}*/

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