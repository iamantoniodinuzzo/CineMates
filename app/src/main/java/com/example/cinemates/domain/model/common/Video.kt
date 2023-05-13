package com.example.cinemates.domain.model.common

import com.google.gson.annotations.SerializedName

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
data class Video(
    val id: String,
    val key: String,
    val type: String,
    val name: String,
    val site: String,
    val isOfficial: Boolean
)