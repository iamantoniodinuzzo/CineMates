package com.indisparte.model.entity


class Cast(
    adult: Boolean,
    val castId: Int,
    val character: String,
    val creditId: String,
    gender: Int,
    id: Int,
    knownForDepartment: String,
    name: String,
    val order: Int,
    val originalName: String,
    popularity: Double,
    profilePath: String?,
) : PersonBase(
    adult = adult,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    popularity = popularity,
    profilePath = profilePath
)