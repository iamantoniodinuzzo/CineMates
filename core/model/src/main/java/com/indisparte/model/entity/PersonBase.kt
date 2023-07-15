package com.indisparte.model.entity

import androidx.annotation.StringRes
import com.indisparte.model.R
import java.text.SimpleDateFormat
import java.util.Locale

enum class Gender(val value: Int, @StringRes val genderResId: Int) {
    MALE(2, R.string.male_gender),
    FEMALE(1, R.string.female_gender),
    OTHER(0, R.string.other_gender)
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

    val formattedGender: Gender
        get() {
            val genderString = when (gender) {
                Gender.MALE.value -> Gender.MALE
                Gender.FEMALE.value -> Gender.FEMALE
                else -> Gender.OTHER
            }
            return genderString
        }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonBase) return false

        return id == other.id && name == other.name
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        return result
    }
}