package com.example.cinemates.domain.model


/**
 * Represents the details of a person
 * @author Antonio Di Nuzzo (Indisparte)
 */
data class Person(
    val adult: Boolean,
    val alsoKnownAs: List<String>,
    val biography: String,
    val birthday: String,
    val deathDay: String,
    val gender: String,
    val homepage: String,
    val id: Int = 0,
    val imdbId: String,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String,
    val popularity: Double,
    val profilePath: String
)

