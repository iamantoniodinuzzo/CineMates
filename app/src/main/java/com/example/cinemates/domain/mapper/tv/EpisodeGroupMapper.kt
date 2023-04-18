package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.EpisodeGroup
import com.example.cinemates.domain.model.EpisodeGroupDetails
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class EpisodeGroupMapper
@Inject
constructor(
    private val networkMapper: NetworkMapper
) : Mapper<EpisodeGroupDTO, EpisodeGroup> {
    override fun map(input: EpisodeGroupDTO): EpisodeGroup {
        return EpisodeGroup(
            description = input.description,
            episodeCount = input.episodeCount,
            groupCount = input.groupCount,
            id = input.id,
            name = input.name,
            network = input.network.let { networkMapper.map(it) }
        )
    }

}*/

fun EpisodeGroupDTO.mapToEpisodeGroup():EpisodeGroup{
    return EpisodeGroup(
        description = this.description,
        episodeCount = this.episodeCount,
        groupCount = this.groupCount,
        id = this.id,
        name = this.name,
        network = this.network.mapToNetwork()
    )
}
