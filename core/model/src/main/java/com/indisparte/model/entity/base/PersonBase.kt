package com.indisparte.model.entity.base

import androidx.annotation.StringRes
import com.indisparte.model.R
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780

enum class Gender(val value: Int, @StringRes val genderResId: Int) {
    NON_BINARY(3, R.string.non_binary_gender),
    MALE(2, R.string.male_gender),
    FEMALE(1, R.string.female_gender),
    OTHER(0, R.string.other_gender)
}


/**
 * @author Antonio Di Nuzzo
 */
abstract class PersonBase(
    val adult: Boolean,
    private val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    private val profilePath: String?,
) : TMDBItem() {

    val completeProfilePathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, profilePath)
    val completeProfilePathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, profilePath)

    val formattedGender: Gender?
        get() = Gender.values().find { it.value == gender }

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

    override fun toString(): String {
        return "PersonBase(adult=$adult, gender=$gender, id=$id, knownForDepartment='$knownForDepartment', name='$name', popularity=$popularity, profilePath=$profilePath)"
    }


}