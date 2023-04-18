package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.GroupDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Group
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class GroupMapper
@Inject
constructor(
    private val episodeMapper: EpisodeMapper
) : Mapper<GroupDTO, Group> {
    override fun map(input: GroupDTO): Group {
        return Group(
            episodes = input.episodes.map {
                episodeMapper.map(it)
            },
            id = input.id,
            locked = input.locked,
            name = input.name,
            order = input.order,
        )
    }

}*/

fun GroupDTO.mapToGroup():Group{
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