package com.example.cinemates.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeGroup(
    @SerialName("description")
    val description: String,
    @SerialName("episode_count")
    val episodeCount: Int,
    @SerialName("group_count")
    val groupCount: Int,
    @SerialName("groups")
    val groups: List<Group>,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("network")
    val network: Network,
    @SerialName("type")
    val type: Int
){
    override fun toString(): String {
        return "EpisodeGroup(description='$description', episodeCount=$episodeCount, groupCount=$groupCount, groups=$groups, id='$id', name='$name', network=$network, type=$type)"
    }
}