package com.example.cinemates.data.remote.response.credits

import com.google.gson.annotations.SerializedName

open class CastDTO(
    adult: Boolean,
    gender: Int?,
    id: Int,
    known_for_department: String,
    name: String,
    popularity: Double,
    profilePath: String?,
    @SerializedName("cast_id")
    val castId: Int,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    val order: Int?,
    @SerializedName("original_name")
    val originalName: String?
) : PersonDTO(
    adult = adult,
    gender = gender,
    id = id,
    known_for_department = known_for_department,
    name = name,
    popularity = popularity,
    profile_path = profilePath,
    also_known_as = listOf()
)