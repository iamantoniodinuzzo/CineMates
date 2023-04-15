package com.example.cinemates.data.remote.response.credits


import kotlinx.serialization.SerialName

class CrewDTO(
    adult: Boolean = false,
    @SerialName("credit_id")
    val creditId: String = "",
    val department: String = "",
    gender: Int = 0,
    id: Int = 0,
    val job: String = "",
    knownForDepartment: String = "",
    name: String = "",
    @SerialName("original_name")
    val originalName: String = "",
    popularity: Double = 0.0,
    profilePath: String? = ""
) : PersonDTO(
    adult,
    id,
    knownForDepartment,
    name,
    popularity,
    profilePath,
    gender
)