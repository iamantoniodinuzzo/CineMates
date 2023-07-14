package com.indisparte.model.entity

import java.text.SimpleDateFormat
import java.util.Locale

private enum class Gender(val value: Int, val gender: String) {
    MALE(2, "Male"),
    FEMALE(1, "Female"),
    OTHER(0, "Other")
}

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class PersonBase(
    val adult: Boolean,
    private val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    val profilePath: String?,
) {
    protected fun getFormattedData(locale: Locale, data: String?): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", locale)
        val date = data?.let { dateFormat.parse(it) }
        return date?.let {
            SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, locale).format(it)
        }
    }

    val formattedGender: String
        get() {
            val genderString = when (gender) {
                Gender.MALE.value -> Gender.MALE.gender
                Gender.FEMALE.value -> Gender.FEMALE.gender
                else -> Gender.OTHER.gender
            }
            return genderString
        }
}