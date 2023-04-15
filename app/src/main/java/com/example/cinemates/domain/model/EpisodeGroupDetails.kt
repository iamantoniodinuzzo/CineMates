package com.example.cinemates.domain.model


import com.example.cinemates.data.remote.response.tvShow.GroupDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class EpisodeGroupDetails(
    val description: String ,
    val episodeCount: Int,
    val groupCount: Int,
    val groups: List<Group> ,
    val id: String ,
    val name: String ,
    val network: Network,
    val type: Int
)