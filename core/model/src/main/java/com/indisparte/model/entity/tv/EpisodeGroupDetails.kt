package com.indisparte.model.entity.tv

import com.indisparte.model.entity.common.Network


class EpisodeGroupDetails(
    description: String,
    episodeCount: Int,
    groupCount: Int,
    val groups: List<Group>,
    id: String,
    name: String,
    network: Network?,
    type: Int,
) : EpisodeGroup(
    description = description,
    episodeCount = episodeCount,
    groupCount = groupCount,
    id = id,
    name = name,
    network = network?.name,
    type = type
)