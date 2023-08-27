package com.indisparte.tv

import com.indisparte.common.Network

/**
 * Represents detailed information about an episode group within a TV show.
 *
 * @property description A brief description of the episode group.
 * @property episodeCount The total number of episodes in the group.
 * @property groupCount The total number of groups the episodes are divided into.
 * @property groups The list of [Group] objects within this episode group.
 * @property id The unique ID associated with the episode group.
 * @property name The name of the episode group.
 * @property network The network associated with the episode group, if available.
 * @property type The type of the episode group as represented by [EpisodeGroupType].
 * @author Antonio Di Nuzzo
 */
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