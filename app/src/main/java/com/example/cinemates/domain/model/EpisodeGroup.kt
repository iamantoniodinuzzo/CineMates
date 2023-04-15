package com.example.cinemates.domain.model


data class EpisodeGroup(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val id: String,
    val name: String,
    val network: Network,
    val type: Int = 0
)