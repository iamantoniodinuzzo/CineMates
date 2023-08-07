package com.indisparte.tv.response


import com.google.gson.annotations.SerializedName

data class GroupDTO(
    val episodes: List<EpisodeDTO>,
    val id: String,
    val locked: Boolean,
    val name: String,
    val order: Int
)