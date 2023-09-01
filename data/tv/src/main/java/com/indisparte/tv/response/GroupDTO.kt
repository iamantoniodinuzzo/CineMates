package com.indisparte.tv.response


data class GroupDTO(
    val episodes: List<EpisodeDTO>,
    val id: String,
    val locked: Boolean,
    val name: String,
    val order: Int
)