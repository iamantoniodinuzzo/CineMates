package com.example.cinemates.data.remote.response.trailer

import com.google.gson.annotations.SerializedName

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
data class VideoDTO(
    val id: String,
    val key: String,
    val type: String,
    val name: String,
    val site: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("is_official")
    val isOfficial: Boolean
)