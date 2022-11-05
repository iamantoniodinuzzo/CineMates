package com.example.cinemates.model.entities

class Cast(
    adult: Boolean,
    gender: Int?,
    id: Int,
    known_for_department: String,
    name: String,
    popularity: Double,
    profile_path: String?,
    var cast_id: Int,
    var character: String,
    var credit_id: String,
    var order: Int,
    var original_name: String
) : Person(
    adult = adult,
    gender = gender,
    id = id,
    known_for_department = known_for_department,
    name = name,
    popularity = popularity,
    profile_path = profile_path
) {
    constructor(id: Int, name: String, profile_path: String?) : this(
        false, null, id, "", name, 0.0, profile_path, 0,
        "", "", 0, ""
    )
}