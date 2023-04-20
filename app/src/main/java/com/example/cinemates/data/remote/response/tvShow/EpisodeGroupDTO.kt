package com.example.cinemates.data.remote.response.tvShow


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class EpisodeGroupDTO(
    val description: String = "",
    @SerializedName("episode_count")
    val episodeCount: Int = 0,
    @SerializedName("group_count")
    val groupCount: Int = 0,
    val id: String = "",
    val name: String = "",
    val network: NetworkDTO,
    val type: Int = 0
)