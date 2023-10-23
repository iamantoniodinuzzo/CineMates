package com.indisparte.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.indisparte.base.Constants.IMAGE_BASE_URL_W500
import com.indisparte.base.Constants.IMAGE_BASE_URL_W780

enum class Gender(
    val value: Int,
    @StringRes val genderResId: Int,
    @DrawableRes val genderDrawableId: Int? = null,
) {
    NON_BINARY(3, R.string.non_binary_gender, R.drawable.ic_non_binary),
    MALE(2, R.string.male_gender, R.drawable.ic_male),
    FEMALE(1, R.string.female_gender, R.drawable.ic_female),
    OTHER(0, R.string.other_gender);

    companion object {
        fun fromValue(value: Int): Gender? = values().firstOrNull { it.value == value }
    }
}


/**
 * Represents basic information about a person.
 * @property adult Indicates if the person is an adult.
 * @property formattedGender The gender of the person.
 * @property id The unique identifier of the person.
 * @property knownForDepartment The department the person is known for.
 * @property name The name of the person.
 * @property popularity The popularity of the person.
 * @property completeProfilePathW780 The complete path to the profile image of the person.
 * @property completeProfilePathW500 The complete path to the profile image of the person.
 * @author Antonio Di Nuzzo
 */
abstract class PersonBase(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    val profilePath: String?,
    var isFavorite: Boolean = false
) : TMDBItem() {

    val completeProfilePathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, profilePath)

    val completeProfilePathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, profilePath)

    val formattedGender: Gender?
        get() = Gender.fromValue(gender)

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

    override fun toString(): String =
        "PersonBase(adult=$adult, gender=$gender, id=$id, knownForDepartment='$knownForDepartment', name='$name', popularity=$popularity, profilePath=$profilePath)"
}