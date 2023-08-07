package com.indisparte.tv.response


import com.google.gson.annotations.SerializedName

data class RoleDTO(
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("episode_count")
    val episodeCount: Int
)