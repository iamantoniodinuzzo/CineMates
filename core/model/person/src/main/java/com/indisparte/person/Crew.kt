package com.indisparte.person

import com.indisparte.base.PersonBase

/**
 * Represents a crew member of a movie or TV show.
 *
 * @property creditId The unique ID of the credit for the crew member.
 * @property department The department in which the crew member works (e.g., "Directing", "Writing").
 * @property job The specific job or role of the crew member (e.g., "Director", "Screenplay").
 * @property originalName The original name of the crew member.
 * @property profilePath The path to the profile image of the crew member.
 * @author Antonio Di Nuzzo
 */
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