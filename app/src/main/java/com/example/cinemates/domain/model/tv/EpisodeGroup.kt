package com.example.cinemates.domain.model.tv



data class EpisodeGroup(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val id: String,
    val name: String,
    val network: Network?,
    val episodeGroupType: String
):java.io.Serializable