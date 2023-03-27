package com.example.cinemates.model

class Cast(
    adult: Boolean,
    gender: Int?,
    id: Int,
    known_for_department: String,
    name: String,
    popularity: Double,
    profile_path: String?,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val order: Int,
    val original_name: String
) : Person(
    adult = adult,
    gender = gender,
    id = id,
    known_for_department = known_for_department,
    name = name,
    popularity = popularity,
    profile_path = profile_path,
    also_known_as = listOf()
) {
    constructor(id: Int, name: String, profile_path: String?) : this(
        false, null, id, "", name, 0.0, profile_path, 0,
        "", "", 0, ""
    )
}