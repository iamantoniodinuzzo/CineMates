package com.indisparte.model.entity.person

import com.indisparte.model.entity.base.PersonBase


class Crew(
    adult: Boolean,
    val creditId: String,
    val department: String,
    gender: Int,
    id: Int,
    val job: String,
    knownForDepartment: String,
    name: String,
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