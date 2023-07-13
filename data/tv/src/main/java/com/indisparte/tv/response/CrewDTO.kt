package com.indisparte.tv.response


import com.google.gson.annotations.SerializedName

data class CrewDTO(
    val adult: Boolean,
    val department: String,
    val gender: Int,
    val id: Int,
    val jobs: List<JobDTO>,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("total_episode_count")
    val totalEpisodeCount: Int
)