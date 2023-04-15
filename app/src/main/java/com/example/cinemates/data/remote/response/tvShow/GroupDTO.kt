package com.example.cinemates.data.remote.response.tvShow


import kotlinx.serialization.Serializable

@Serializable
data class GroupDTO(
    val episodes: List<EpisodeDTO> = listOf(),
    val id: String = "",
    val locked: Boolean = false,
    val name: String = "",
    val order: Int = 0
)