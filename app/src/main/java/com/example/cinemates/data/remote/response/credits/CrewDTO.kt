package com.example.cinemates.data.remote.response.credits

import com.google.gson.annotations.SerializedName

class CrewDTO(
    adult: Boolean,
    gender: Int?,
    id: Int,
    knownForDepartment: String,
    name: String,
    popularity: Double,
    profilePath: String?,
    @SerializedName("credit_id")
    val creditId: String,
    val department: String,
    val job: String,
    @SerializedName("original_name")
    val originalName: String
) : PersonDTO(
    adult = adult,
    gender = gender,
    id = id,
    known_for_department = knownForDepartment,
    name = name,
    popularity = popularity,
    profile_path = profilePath
){
    override fun toString(): String {
        return "CrewDTO(credit_id='$creditId', department='$department', job='$job', original_name='$originalName')"
    }
}