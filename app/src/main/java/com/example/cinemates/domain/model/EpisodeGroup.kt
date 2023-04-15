package com.example.cinemates.domain.model


data class EpisodeGroup(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val groups: List<Group>,
    val id: String,
    val name: String,
    val network: Network,
)