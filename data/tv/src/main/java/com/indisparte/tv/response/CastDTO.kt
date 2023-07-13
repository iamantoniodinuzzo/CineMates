package com.indisparte.tv.response


import com.google.gson.annotations.SerializedName

data class CastDTO(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String?,
    val roles: List<RoleDTO>,
    @SerializedName("total_episode_count")
    val totalEpisodeCount: Int
)