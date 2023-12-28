package com.indisparte.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

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
        /**
         * Retrieves a [Gender] based on its numerical value.
         *
         * @param value The numerical value of the gender type.
         * @return The corresponding [Gender] enum value.
         * @throws NoSuchElementException If no gender type with the specified value is found.
         */
        @Throws(NoSuchElementException::class)
        fun fromValue(value: Int): Gender {
            return values().find { it.value == value }
                ?: throw NoSuchElementException("There is no gender type with this value: $value")
        }
    }
}


/**
 * Represents basic information about a person.
 * @param adult Indicates whether the person is an adult.
 * @param gender The gender of the person as an integer value.
 * @param id The unique identifier for the person.
 * @param knownForDepartment The department for which the person is known.
 * @param name The name of the person.
 * @param popularity The popularity score of the person.
 * @param profilePath The relative path to the profile image of the person, or null if not available.
 * @param isFavorite Indicates whether the person is marked as a favorite.
 *
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
    var isFavorite: Boolean = false,
) : TMDBItem() {

    /**
     * Gets the complete profile image path for the person with a width of 780 pixels.
     *
     * @return The complete image path, or null if the relative path is empty.
     */
    val completeProfilePathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, profilePath)

    /**
     * Gets the complete profile image path for the person with a width of 500 pixels.
     *
     * @return The complete image path, or null if the relative path is empty.
     */
    val completeProfilePathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, profilePath)

    /**
     * Gets the formatted gender of the person.
     *
     * @return The [Gender] enum value representing the gender.
     */
    val formattedGender: Gender
        get() = Gender.fromValue(gender)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonBase) return false

        return (id == other.id) && (name == other.name)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String =
        "PersonBase(adult=$adult, gender=$gender, id=$id, knownForDepartment='$knownForDepartment', name='$name', popularity=$popularity, profilePath=$profilePath)"
}