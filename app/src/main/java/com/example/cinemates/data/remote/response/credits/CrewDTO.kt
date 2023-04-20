package com.example.cinemates.data.remote.response.credits


import com.google.gson.annotations.SerializedName

class CrewDTO(
    adult: Boolean,
    @SerializedName("credit_id")
    val creditId: String,
    val department: String,
    gender: Int?,
    id: Int,
    val job: String,
    knownForDepartment: String,
    name: String,
    @SerializedName("original_name")
    val originalName: String,
    popularity: Double,
    profilePath: String?
) : PersonDTO(
    adult,
    id,
    knownForDepartment,
    name,
    popularity,
    profilePath,
    gender
)