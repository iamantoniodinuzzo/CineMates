package com.example.cinemates.data.remote.response.tvShow


import kotlinx.serialization.SerialName

data class EpisodeGroupDTO(
    @SerialName("description")
    val description: String,
    @SerialName("episode_count")
    val episodeCount: Int,
    @SerialName("group_count")
    val groupCount: Int,
    @SerialName("groupDTOS")
    val groupDTOS: List<GroupDTO>,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("networkDTO")
    val networkDTO: NetworkDTO,
    @SerialName("type")
    val type: Int
)