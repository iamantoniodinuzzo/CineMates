package com.indisparte.model.entity



data class EpisodeGroup(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val id: String,
    val name: String,
    val network: String?,
    val type: Int
)