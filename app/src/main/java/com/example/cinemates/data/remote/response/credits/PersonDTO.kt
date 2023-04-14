package com.example.cinemates.data.remote.response.credits

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

open class PersonDTO(
    val adult: Boolean,
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>,
    val biography: String?,
    private val birthday: String?,
    private val deathDay: String?,
    val gender: Int?,
    val homepage: String?,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    val name: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String?,
) : Serializable {

    val knownAs: String
        get() = alsoKnownAs.stream().collect(Collectors.joining(" - "))

    val age: String
        get() {
            return if (deathDay == null && birthday != null) {
                val birthdayDate = LocalDate.parse(birthday)
                val age = Period.between(
                    birthdayDate,
                    LocalDate.now()
                ).years
                age.toString()
            } else if (birthday != null) {
                val birthdayDate = LocalDate.parse(birthday)
                val deathDayDate = LocalDate.parse(deathDay)
                val age = Period.between(
                    birthdayDate,
                    deathDayDate
                ).years
                age.toString()
            } else ""
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
            return if (deathDay != null) {
                val localDate =
                    LocalDate.parse(deathDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
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

    constructor(gender: Int?, id: Int, name: String, profilePath: String?) : this(
        false,
        listOf(),
        null,
        null,
        null,
        gender,
        null,
        id,
        null,
        null,
        name,
        null,
        0.0,
        profilePath
    )


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonDTO) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "PersonDTO(birthday=$birthday, deathDay=$deathDay, gender=$gender, id=$id, name='$name', profile_path=$profilePath)"
    }

}