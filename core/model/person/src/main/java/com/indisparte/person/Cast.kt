package com.indisparte.person

import com.indisparte.base.PersonBase


/**
 * Represents a cast member of a movie or TV show.
 *
 * @property castId The unique ID of the cast member.
 * @property character The name of the character played by the cast member.
 * @property creditId The unique ID of the credit for the cast member.
 * @property order The order in which the cast member appears in the credits.
 * @property originalName The original name of the cast member.
 * @property profilePath The path to the profile image of the cast member.
 * @author Antonio Di Nuzzo
 */
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
