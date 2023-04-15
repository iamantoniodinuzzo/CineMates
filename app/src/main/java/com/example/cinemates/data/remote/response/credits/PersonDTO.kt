package com.example.cinemates.data.remote.response.credits


import kotlinx.serialization.SerialName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Represents the details of a person
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class PersonDTO(
    val adult: Boolean = false,
    @SerialName("also_known_as")
    val alsoKnownAs: List<String> = listOf(),
    val biography: String = "",
    private val birthday: String = "",
    @SerialName("deathday")
    private val deathDay: String? = "",
    private val gender: Int = 0,
    val homepage: String? = "",
    val id: Int = 0,
    @SerialName("imdb_id")
    val imdbId: String = "",
    @SerialName("known_for_department")
    val knownForDepartment: String = "",
    val name: String = "",
    @SerialName("place_of_birth")
    val placeOfBirth: String = "",
    val popularity: Double = 0.0,
    @SerialName("profile_path")
    val profilePath: String? = ""
) {
    constructor(
        adult: Boolean,
        id: Int,
        knownForDepartment: String,
        name: String,
        popularity: Double,
        profilePath: String?,
        gender: Int
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

    val formattedGender: String
        get() {
            return if (gender == 1) {
                "Female"
            } else {
                "Male"
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
}

