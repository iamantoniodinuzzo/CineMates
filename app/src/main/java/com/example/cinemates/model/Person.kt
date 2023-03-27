package com.example.cinemates.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

open class Person(
    val adult: Boolean,
    val also_known_as: List<String>,
    val biography: String?,
    val birthday: String?,
    val deathday: String?,
    val gender: Int?,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val known_for_department: String,
    val name: String,
    val place_of_birth: String?,
    val popularity: Double,
    val profile_path: String?,
) : Serializable {

    val knownAs: String
        get() = also_known_as.stream().collect(Collectors.joining(" - "))

    val age: String
        get() {
            return if (deathday == null && birthday!=null) {
                val birthdayDate = LocalDate.parse(birthday)
                val age = Period.between(
                    birthdayDate,
                    LocalDate.now()
                ).years
                age.toString()
            } else if (birthday != null) {
                val birthdayDate = LocalDate.parse(birthday)
                val deathDayDate = LocalDate.parse(deathday)
                val age = Period.between(
                    birthdayDate,
                    deathDayDate
                ).years
                age.toString()
            }else ""
        }
    val formattedBirthday: String
        get() {
            return if (birthday != null) {
                val localDate =
                    LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val pattern = DateTimeFormatter.ofPattern("dd MMM, yyyy")
                pattern.format(localDate)
            } else ""
        }

    val formattedDeathday: String
        get() {
            return if (deathday != null) {
                val localDate =
                    LocalDate.parse(deathday, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val pattern = DateTimeFormatter.ofPattern("dd MMM, yyyy")
                pattern.format(localDate)
            } else ""
        }

    constructor(
        adult: Boolean,
        gender: Int?,
        id: Int,
        known_for_department: String,
        name: String,
        popularity: Double,
        profile_path: String?
    ) : this(
        adult, listOf(), null, "", "", gender, null, id, null,
        known_for_department, name, null, popularity, profile_path
    )

    constructor(
        id: Int,
        name: String,
        profile_path: String?,
    ) : this(
        false, arrayListOf(), null, null, null, null, null, id, null,
        "", name, null, 0.0, profile_path
    )

    constructor(
        adult: Boolean,
        gender: Int?,
        id: Int,
        known_for_department: String,
        name: String,
        popularity: Double,
        profile_path: String?,
        also_known_as: List<String>
    ) : this(adult, gender, id, known_for_department, name, popularity, profile_path)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Person(birthday=$birthday, deathDay=$deathday, gender=$gender, id=$id, name='$name', profile_path=$profile_path)"
    }

}