package com.indisparte.person

import android.os.Build
import androidx.annotation.RequiresApi
import com.indisparte.common.Poster
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

/**
 * Represents detailed information about a person in the entertainment industry, including biography,
 * birth and death details, and other personal information.
 *
 * @property adult Indicates whether the person's content is intended for adult audiences.
 * @property alsoKnownAs List of alternative names by which the person is known.
 * @property biography The biography of the person.
 * @property birthday The date of birth of the person.
 * @property deathDay The date of death of the person (if applicable).
 * @property gender The gender of the person.
 * @property homepage The personal website of the person.
 * @property id The unique ID associated with the person.
 * @property imdbId The IMDB ID of the person.
 * @property knownForDepartment The department in which the person is known for working.
 * @property name The name of the person.
 * @property placeOfBirth The place of birth of the person.
 * @property popularity The popularity score of the person.
 * @property profilePath The path to the profile image of the person.
 * @property images List of poster images associated with the person.
 * @author Antonio Di Nuzzo
 */
class PersonDetails(
    adult: Boolean,
    val alsoKnownAs: List<String>,
    val biography: String,
    private val birthday: String?,
    private val deathDay: String?,
    gender: Int,
    val homepage: String?,
    id: Int,
    val imdbId: String,
    knownForDepartment: String,
    name: String,
    val placeOfBirth: String?,
    popularity: Double,
    profilePath: String?,
    val images: List<Poster>,
) : Person(
    adult = adult,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    popularity = popularity,
    profilePath = profilePath,
) {


    val formattedBirthDay: String?
        get() {
            return formatDate(birthday)
        }
    val formattedDeathDay: String?
        get() {
            return formatDate(deathDay)
        }
    val age: String
        get() {
            return calculateAgeCompat()
        }

    private fun calculateAge(): String {
        val dateFormat = SimpleDateFormat(TMDB_DATE_TIME_FORMAT, Locale.getDefault())

        val today = Calendar.getInstance()

        val endDate = if (deathDay != null) dateFormat.parse(deathDay) else today.time
        val startDate = dateFormat.parse(birthday ?: return "")

        val calendarStart = Calendar.getInstance().apply {
            if (startDate != null) {
                time = startDate
            }
        }
        val calendarEnd = Calendar.getInstance().apply {
            if (endDate != null) {
                time = endDate
            }
        }

        val age = calendarEnd[Calendar.YEAR] - calendarStart[Calendar.YEAR]


        return if (age > 0) age.toString() else ""
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAgeApiO(): String {
        val dateFormat =
            DateTimeFormatter.ofPattern(TMDB_DATE_TIME_FORMAT, Locale.getDefault())

        val today = LocalDate.now()

        val endDate = deathDay?.let { LocalDate.parse(it, dateFormat) } ?: today
        val startDate: LocalDate = try {
            LocalDate.parse(birthday, dateFormat)
        } catch (e: NullPointerException) {
            return ""
        }

        val period = Period.between(startDate, endDate)
        val years = period.years

        return if (years >= 0) years.toString() else ""
    }

    private fun calculateAgeCompat(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            calculateAgeApiO()
        } else {
            calculateAge()
        }
    }

    override fun toString(): String {
        return "PersonDetails(" +
                "alsoKnownAs=$alsoKnownAs, " +
                "biography='$biography', " +
                "birthday=$birthday, " +
                "deathDay=$deathDay, " +
                "homepage=$homepage, " +
                "imdbId='$imdbId', " +
                "placeOfBirth=$placeOfBirth, " +
                "images=$images" +
                ")" +
                " ${super.toString()}"
    }


}