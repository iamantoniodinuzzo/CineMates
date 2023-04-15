package com.example.cinemates.data.remote.response.tvShow


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class EpisodeGroupDetailsDTO(
    val description: String = "",
    @SerialName("episode_count")
    val episodeCount: Int = 0,
    @SerialName("group_count")
    val groupCount: Int = 0,
    val groups: List<GroupDTO> = listOf(),
    val id: String = "",
    val name: String = "",
    val network: NetworkDTO,
    val type: Int = 0
)