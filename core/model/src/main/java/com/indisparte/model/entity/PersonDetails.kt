package com.indisparte.model.entity

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


class PersonDetails(
    adult: Boolean,
    val alsoKnownAs: List<String>,
    val biography: String,
    private val birthday: String?,
    private val `death-day`: String?,
    gender: Int,
    val homepage: String?,
    id: Int,
    val imdbId: String,
    knownForDepartment: String,
    name: String,
    val placeOfBirth: String?,
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
) {
    val formattedBirthDay: String?
        get() {
            return getFormattedData(Locale.getDefault(), birthday)
        }
    val formattedDeathDay: String?
        get() {
            return getFormattedData(Locale.getDefault(), `death-day`)
        }
    val age: String
        get() {
            return calculateAgeCompat()
        }

    private fun calculateAge(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val today = Calendar.getInstance()

        val endDate = if (`death-day` != null) dateFormat.parse(`death-day`) else today.time
        val startDate = dateFormat.parse(birthday ?: return "N/A")

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

        val age = calendarEnd.get(Calendar.YEAR) - calendarStart.get(Calendar.YEAR)
        val startMonth = calendarStart.get(Calendar.MONTH)
        val endMonth = calendarEnd.get(Calendar.MONTH)
        val startDay = calendarStart.get(Calendar.DAY_OF_MONTH)
        val endDay = calendarEnd.get(Calendar.DAY_OF_MONTH)

        val adjust = if (endMonth < startMonth || (endMonth == startMonth && endDay < startDay)) -1 else 0

        return if (age >= 0) age.toString() else ""
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAgeApiO(): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())

        val today = LocalDate.now()

        val endDate = `death-day`?.let { LocalDate.parse(it, dateFormat) } ?: today
        val startDate = LocalDate.parse(birthday, dateFormat)

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
}