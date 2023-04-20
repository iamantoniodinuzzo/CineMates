package com.example.cinemates.data.remote.response.tvShow


class EpisodeGroupDetailsDTO(
    description: String,
    episodeCount: Int,
    groupCount: Int,
    val groups: List<GroupDTO>,
    id: String,
    name: String,
    network: NetworkDTO,
    type: Int
) : EpisodeGroupDTO(
    description,
    episodeCount,
    groupCount,
    id,
    name,
    network,
    type
)