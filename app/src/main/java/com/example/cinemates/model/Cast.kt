package com.example.cinemates.model

import com.google.gson.annotations.SerializedName

open class Cast(
    adult: Boolean,
    gender: Int?,
    id: Int,
    known_for_department: String,
    name: String,
    popularity: Double,
    profilePath: String?,
    @SerializedName("cast_id")
    val castId: Int?,
    val character: String?,
    @SerializedName("credit_id")
    val creditId: String,
    val order: Int?,
    @SerializedName("original_name")
    val originalName: String?
) : Person(
    adult = adult,
    gender = gender,
    id = id,
    known_for_department = known_for_department,
    name = name,
    popularity = popularity,
    profile_path = profilePath,
    also_known_as = listOf()
) {
    constructor(id: Int, name: String, profile_path: String?) : this(
        false, null, id, "", name, 0.0, profile_path, 0,
        "", "", 0, ""
    )

    constructor(creditId: String, gender: Int, id: Int, name: String, profilePath: String) : this(
        false,
        gender,
        id,
        "",
        name,
        0.0,
        profilePath,
        null,
        null,
        creditId,
        null,
        null

    )

    override fun toString(): String {
        return "Cast(cast_id=$castId, character='$character', credit_id='$creditId', order=$order, original_name='$originalName')"
    }


}