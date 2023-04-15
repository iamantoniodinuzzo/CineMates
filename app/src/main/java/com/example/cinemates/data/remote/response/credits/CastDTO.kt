package com.example.cinemates.data.remote.response.credits


import kotlinx.serialization.SerialName

class CastDTO(
    adult: Boolean = false,
    @SerialName("cast_id")
    val castId: Int = 0,
    val character: String = "",
    @SerialName("credit_id")
    val creditId: String = "",
    gender: Int = 0,
    id: Int = 0,
    knownForDepartment: String = "",
    name: String = "",
    val order: Int = 0,
    @SerialName("original_name")
    val originalName: String = "",
    popularity: Double = 0.0,
    profilePath: String? = null
) : PersonDTO(
    adult,
    id,
    knownForDepartment,
    name,
    popularity,
    profilePath,
    gender
)