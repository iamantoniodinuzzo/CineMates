package com.indisparte.model.entity.person

import com.indisparte.model.entity.base.PersonBase


class Person(
    adult: Boolean,
    gender: Int,
    id: Int,
    val knownFor: List<KnownFor>,
    knownForDepartment: String,
    name: String,
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
){
    override fun toString(): String {
        return "Person(" +
                "knownFor=$knownFor" +
                ")"
    }
}

