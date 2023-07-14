package com.indisparte.model.entity


class Person(
    adult: Boolean,
    gender: Int,
    id: Int,
    val knownFor: List<KnownFor>,
    knownForDepartment: String,
    name: String,
    popularity: Double,
    profilePath: String,
) : PersonBase(
    adult = adult,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    popularity = popularity,
    profilePath = profilePath
)

