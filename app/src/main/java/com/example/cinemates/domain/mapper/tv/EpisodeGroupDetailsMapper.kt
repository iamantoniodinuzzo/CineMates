package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupDTO
import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupDetailsDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.EpisodeGroup
import com.example.cinemates.domain.model.EpisodeGroupDetails
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class EpisodeGroupDetailsMapper
@Inject
constructor(
    private val networkMapper: NetworkMapper,
    private val groupMapper: GroupMapper
) : Mapper<EpisodeGroupDetailsDTO, EpisodeGroupDetails> {
    override fun map(input: EpisodeGroupDetailsDTO): EpisodeGroupDetails {
        return EpisodeGroupDetails(
            description = input.description,
            episodeCount = input.episodeCount,
            groupCount = input.groupCount,
            id = input.id,
            name = input.name,
            network = input.network.let { networkMapper.map(it) },
            type = input.type,
            groups = input.groups.map {
                groupMapper.map(it)
            }
        )
    }

}