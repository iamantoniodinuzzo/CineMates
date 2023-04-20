package com.example.cinemates.data.remote.response.credits


import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

/**
 * Represents the details of a person
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class PersonDTO(
    val adult: Boolean = false,
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String> = listOf(),
    val biography: String = "",
    private val birthday: String = "",
    @SerializedName("deathday")
    private val deathDay: String? = "",
    private val gender: Int?,
    val homepage: String? = "",
    val id: Int = 0,
    @SerializedName("imdb_id")
    val imdbId: String = "",
    @SerializedName("known_for_department")
    val knownForDepartment: String = "",
    val name: String = "",
    @SerializedName("place_of_birth")
    val placeOfBirth: String = "",
    val popularity: Double = 0.0,
    @SerializedName("profile_path")
    val profilePath: String? = ""
) {
    constructor(
        adult: Boolean,
        id: Int,
        knownForDepartment: String,
        name: String,
        popularity: Double,
        profilePath: String?,
        gender: Int?
    ) : this(
        adult,
        listOf(),
        "",
        "",
        "",
        gender,
        "",
        id,
        "",
        knownForDepartment,
        name,
        "",
        popularity,
        profilePath
    )

    private fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat.getDateInstance()
        val formattedDate = inputFormat.parse(date)
        return outputFormat.format(formattedDate)
    }

    private fun calculateAge(dateOfBirth: String, currentDate: String): String {
        val birthDate = LocalDate.parse(dateOfBirth)
        val today = LocalDate.parse(currentDate)
        val age = Period.between(birthDate, today).years
        return age.toString()
    }

    val formattedGender: String
        get() {
            return when (gender) {
                1 -> {
                    "Female"
                }
                2 -> {
                    "Male"
                }
                else -> {
                    "Other"
                }
            }
        }

    val formattedBirthday: String
        get() {
            return formatDate(birthday)
        }

    val formattedDeathDay: String
        get() {
            return deathDay?.let {
                formatDate(it)
            } ?: ""
        }

    val age: String
        get() {
            return if (deathDay == null || deathDay.isEmpty()) {
                calculateAge(birthday, LocalDate.now().toString())
            } else {
                calculateAge(birthday, deathDay)
            }
        }
}

