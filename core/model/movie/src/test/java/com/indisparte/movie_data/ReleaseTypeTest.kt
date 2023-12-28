package com.indisparte.movie_data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class ReleaseTypeTest {

    @Test
    fun `fromValue should return correct ReleaseType for valid value`() {
        // Given
        val validValue = 3

        // When
        val result = ReleaseType.fromValue(validValue)

        // Then
        assertEquals(ReleaseType.THEATRICAL, result)
    }

    @Test
    fun `fromValue should throw NoSuchElementException for invalid value`() {
        // Given
        val invalidValue = 7

        // When / Then
        assertThrows(NoSuchElementException::class.java) {
            ReleaseType.fromValue(invalidValue)
        }
    }

    @Test
    fun `fromValue should return correct ReleaseType for another valid value`() {
        // Given
        val anotherValidValue = 4

        // When
        val result = ReleaseType.fromValue(anotherValidValue)

        // Then
        assertEquals(ReleaseType.DIGITAL, result)
    }

    // Add more tests as needed for other values and edge cases
}