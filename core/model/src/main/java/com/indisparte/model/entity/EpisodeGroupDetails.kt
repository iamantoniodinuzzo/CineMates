package com.indisparte.model.entity



data class EpisodeGroupDetails(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val groups: List<Group>,
    val id: String,
    val name: String,
    val network: Network?,
    val type: Int
)