package com.indisparte.tv.response


import com.google.gson.annotations.SerializedName

data class EpisodeGroupDTO(
    val description: String,
    @SerializedName("episode_count")
    val episodeCount: Int,
    @SerializedName("group_count")
    val groupCount: Int,
    val id: String,
    val name: String,
    val network: String?,
    val type: Int
)