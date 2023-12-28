package com.indisparte.base

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class GenderTest{

    @Test
    fun `fromValue should return correct Gender for valid value`() {
        // Given
        val value = 2

        // When
        val result = Gender.fromValue(value)

        // Then
        assertEquals(Gender.MALE, result)
    }

    @Test
    fun `fromValue should throw NoSuchElementException for invalid value`() {
        // Given
        val invalidValue = 99

        // When, Then
        assertThrows(NoSuchElementException::class.java) {
            Gender.fromValue(invalidValue)
        }
    }

    @Test
    fun `NON_BINARY should have correct properties`() {
        // Given
        val gender = Gender.NON_BINARY

        // When, Then
        assertEquals(3, gender.value)
        assertEquals(R.string.non_binary_gender, gender.genderResId)
        assertEquals(R.drawable.ic_non_binary, gender.genderDrawableId)
    }

    @Test
    fun `MALE should have correct properties`() {
        // Given
        val gender = Gender.MALE

        // When, Then
        assertEquals(2, gender.value)
        assertEquals(R.string.male_gender, gender.genderResId)
        assertEquals(R.drawable.ic_male, gender.genderDrawableId)
    }

    @Test
    fun `FEMALE should have correct properties`() {
        // Given
        val gender = Gender.FEMALE

        // When, Then
        assertEquals(1, gender.value)
        assertEquals(R.string.female_gender, gender.genderResId)
        assertEquals(R.drawable.ic_female, gender.genderDrawableId)
    }

    @Test
    fun `OTHER should have correct properties`() {
        // Given
        val gender = Gender.OTHER

        // When, Then
        assertEquals(0, gender.value)
        assertEquals(R.string.other_gender, gender.genderResId)
        assertEquals(null, gender.genderDrawableId)
    }
}