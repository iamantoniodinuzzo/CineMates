package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.EpisodeGroup
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class EpisodeGroupMapper
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

}