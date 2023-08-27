package com.indisparte.model.entity.person

import com.indisparte.model.entity.base.PersonBase

/**
 * Represents a person involved in the entertainment industry, such as an actor, director, or crew member.
 *
 * @property adult Indicates whether the person's content is intended for adult audiences.
 * @property gender The gender of the person.
 * @property id The unique ID associated with the person.
 * @property knownForDepartment The department in which the person is known for working.
 * @property name The name of the person.
 * @property popularity The popularity score of the person.
 * @property profilePath The path to the profile image of the person.
 * @author Antonio Di Nuzzo
 */
class Person(
    adult: Boolean,
    gender: Int,
    id: Int,
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
)

