package com.example.cinemates.model.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

@Entity
open class Person(
    @Ignore
    val adult: Boolean,
    @Ignore
    val also_known_as: List<String>,
    @Ignore
    val biography: String?,
    @Ignore
    val birthday: String?,
    @Ignore
    val deathday: String?,
    @Ignore
    val gender: Int?,
    @Ignore
    val homepage: String?,
    @PrimaryKey
    val id: Int,
    @Ignore
    val imdb_id: String?,
    @Ignore
    val known_for_department: String,
    val name: String,
    @Ignore
    val place_of_birth: String?,
    @Ignore
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

    @Ignore
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