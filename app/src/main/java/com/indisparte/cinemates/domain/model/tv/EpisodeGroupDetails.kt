package com.indisparte.cinemates.domain.model.tv




data class EpisodeGroupDetails(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val groups: List<Group>,
    val id: String,
    val name: String,
    val network: Network?,
    val episodeGroupType: String
)