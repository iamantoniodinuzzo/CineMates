package com.indisparte.tv.response


import com.google.gson.annotations.SerializedName

data class EpisodeGroupDetailsDTO(
    val description: String,
    @SerializedName("episode_count")
    val episodeCount: Int,
    @SerializedName("group_count")
    val groupCount: Int,
    val groups: List<GroupDTO>,
    val id: String,
    val name: String,
    val network: NetworkDTO?,
    val type: Int
)