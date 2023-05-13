package com.example.cinemates.data.remote.response.credits

import com.google.gson.annotations.SerializedName


open class CastDTO(
    adult: Boolean,
    @SerializedName("cast_id")
    val castId: Int,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    gender: Int?,
    id: Int,
    knownForDepartment: String,
    name: String,
    val order: Int,
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
) {
    constructor(
        adult: Boolean, character: String,
        creditId: String, gender: Int?, id: Int,
        knownForDepartment: String, name: String,
        order: Int, originalName: String, popularity: Double, profilePath: String?
    ) : this(
        adult,
        0,
        character,
        creditId,
        gender,
        id,
        knownForDepartment,
        name,
        order,
        originalName,
        popularity,
        profilePath
    )
}