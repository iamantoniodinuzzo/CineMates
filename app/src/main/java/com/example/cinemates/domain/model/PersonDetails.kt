package com.example.cinemates.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

data class PersonDetails(
    val adult: Boolean,
    val alsoKnownAs: List<String>,
    val biography: String,
    val birthday: String,
    val deathDay: String,
    val gender: String,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String,
    val popularity: Double,
    val profilePath: String?,
    val age:String
)