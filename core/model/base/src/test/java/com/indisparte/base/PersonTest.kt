package com.indisparte.base

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class PersonTest {

    private lateinit var profilePath: String
    private lateinit var completeProfilePathW500: String
    private lateinit var completeProfilePathW780: String

    @Before
    fun setUp() {
        profilePath = "/example_path.jpg"
        completeProfilePathW500 = TMDBItem.IMAGE_BASE_URL_W500 + profilePath
        completeProfilePathW780 = TMDBItem.IMAGE_BASE_URL_W780 + profilePath
    }

    @Test
    fun `completeProfilePathW780 should return correct complete image path`() {
        // Given
        val person = Person(
            adult = false,
            gender = Gender.MALE.value,
            id = 123,
            knownForDepartment = "Actor",
            name = "John Doe",
            popularity = 8.5,
            profilePath = profilePath
        )

        // When, Then
        assertEquals(completeProfilePathW780, person.completeProfilePathW780)
    }

    @Test
    fun `completeProfilePathW500 should return correct complete image path`() {
        // Given
        val person = Person(
            adult = false,
            gender = Gender.FEMALE.value,
            id = 456,
            knownForDepartment = "Director",
            name = "Jane Doe",
            popularity = 7.5,
            profilePath = profilePath
        )

        // When, Then
        assertEquals(completeProfilePathW500, person.completeProfilePathW500)
    }

    @Test
    fun `formattedGender should return correct Gender enum value`() {
        // Given
        val personMale = Person(
            adult = true,
            gender = Gender.MALE.value,
            id = 789,
            knownForDepartment = "Writer",
            name = "Chris Smith",
            popularity = 9.0,
            profilePath = "/male_profile.jpg"
        )

        val personFemale = Person(
            adult = true,
            gender = Gender.FEMALE.value,
            id = 101,
            knownForDepartment = "Producer",
            name = "Emily Johnson",
            popularity = 8.0,
            profilePath = "/female_profile.jpg"
        )

        // When, Then
        assertEquals(Gender.MALE, personMale.formattedGender)
        assertEquals(Gender.FEMALE, personFemale.formattedGender)
    }

    @Test
    fun `equals should return true for equal Person objects`() {
        // Given
        val person1 = Person(
            adult = false,
            gender = Gender.MALE.value,
            id = 111,
            knownForDepartment = "Actor",
            name = "John Doe",
            popularity = 8.5,
            profilePath = profilePath
        )

        val person2 = Person(
            adult = false,
            gender = Gender.MALE.value,
            id = 111,
            knownForDepartment = "Actor",
            name = "John Doe",
            popularity = 8.5,
            profilePath = profilePath
        )

        // When, Then
        assertEquals(person1, person2)
    }

    @Test
    fun `hashCode should be equal for equal Person objects`() {
        // Given
        val person1 = Person(
            adult = false,
            gender = Gender.MALE.value,
            id = 222,
            knownForDepartment = "Director",
            name = "Jane Doe",
            popularity = 7.5,
            profilePath = "/another_path.jpg"
        )

        val person2 = Person(
            adult = false,
            gender = Gender.MALE.value,
            id = 222,
            knownForDepartment = "Director",
            name = "Jane Doe",
            popularity = 7.5,
            profilePath = "/another_path.jpg"
        )

        // When, Then
        assertEquals(person1.hashCode(), person2.hashCode())
    }

    @Test
    fun `equals should return false for different Person objects`() {
        // Given
        val person1 = Person(
            adult = true,
            gender = Gender.FEMALE.value,
            id = 333,
            knownForDepartment = "Writer",
            name = "Chris Smith",
            popularity = 9.0,
            profilePath = "/male_profile.jpg"
        )

        val person2 = Person(
            adult = false,
            gender = Gender.MALE.value,
            id = 444,
            knownForDepartment = "Producer",
            name = "Emily Johnson",
            popularity = 8.0,
            profilePath = "/female_profile.jpg"
        )

        // When, Then
        assertNotEquals(person1, person2)
    }
}