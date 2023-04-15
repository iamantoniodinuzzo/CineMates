package com.example.cinemates.data.remote.response.credits

import com.google.gson.annotations.SerializedName

open class CastDTO(
    val adult: Boolean,
    val gender: Int?,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profilePath: String?,
    @SerializedName("cast_id")
    val castId: Int,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    val order: Int?,
    @SerializedName("original_name")
    val originalName: String?
)