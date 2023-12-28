package com.indisparte.person

import com.indisparte.base.Gender
import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class PersonDetailsTest{
    @Test
    fun `calculateAgeCompat should return correct age for a person with birth and death dates`() {
        // Given
        val personDetails = PersonDetails(
            adult = true,
            gender = Gender.MALE.value,
            id = 1,
            knownForDepartment = "Actor",
            name = "John Doe",
            popularity = 85.0,
            profilePath = "/profile.jpg",
            alsoKnownAs = emptyList(),
            biography = "A talented actor",
            birthday = "1980-01-01",
            deathDay = "2022-01-01",
            homepage = "https://www.johndoe.com",
            imdbId = "nm1234567",
            placeOfBirth = "Los Angeles, CA",
            images = emptyList()
        )

        // When
        val result = personDetails.age

        // Then
        assertEquals("42", result)
    }

    @Test
    fun `calculateAgeCompat should return empty string for a person with missing birth date`() {
        // Given
        val personDetails = PersonDetails(
            adult = true,
            gender = Gender.MALE.value,
            id = 1,
            knownForDepartment = "Actor",
            name = "John Doe",
            popularity = 85.0,
            profilePath = "/profile.jpg",
            alsoKnownAs = emptyList(),
            biography = "A talented actor",
            birthday = null,
            deathDay = "2022-01-01",
            homepage = "https://www.johndoe.com",
            imdbId = "nm1234567",
            placeOfBirth = "Los Angeles, CA",
            images = emptyList()
        )

        // When
        val result = personDetails.age

        // Then
        assertEquals("", result)
    }

    @Test
    fun `calculateAgeCompat should return correct age for a person with birth date only`() {
        // Given
        val personDetails = PersonDetails(
            adult = true,
            gender = Gender.MALE.value,
            id = 1,
            knownForDepartment = "Actor",
            name = "John Doe",
            popularity = 85.0,
            profilePath = "/profile.jpg",
            alsoKnownAs = emptyList(),
            biography = "A talented actor",
            birthday = "1980-01-01",
            deathDay = null,
            homepage = "https://www.johndoe.com",
            imdbId = "nm1234567",
            placeOfBirth = "Los Angeles, CA",
            images = emptyList()
        )

        // When
        val result = personDetails.age

        // Then
        assertEquals("43", result)
    }

}