package com.indisparte.model.entity.tv

import com.indisparte.model.entity.base.PersonBase

/**
 * Represents a person who was credited as the creator of a media entity, such as a TV show.
 *
 * @property creditId The ID associated with the credit of the creator.
 * @property gender The gender of the creator.
 * @property id The unique ID associated with the creator.
 * @property name The name of the creator.
 * @property profilePath The path to the profile image of the creator.
 * @author Antonio Di Nuzzo
 */
class CreatedBy(
    val creditId: String,
    gender: Int,
    id: Int,
    name: String,
    profilePath: String?,
) : PersonBase(
    adult = false,
    gender,
    id,
    knownForDepartment = "",
    name,
    popularity = 0.0,
    profilePath
)