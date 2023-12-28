package com.indisparte.base

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class MediaTypeTest {

    @Test
    fun `fromId should return correct MediaType for valid id`() {
        // Given
        val id = 1

        // When
        val result = MediaType.fromId(id)

        // Then
        assertEquals(MediaType.MOVIE, result)
    }

    @Test
    fun `fromId should throw NoSuchElementException for invalid id`() {
        // Given
        val invalidId = 99

        // When, Then
        assertThrows(NoSuchElementException::class.java){
            MediaType.fromId(invalidId)
        }
    }

    @Test
    fun `fromId should return correct MediaType for MOVIE_TV`() {
        // Given
        val id = 0

        // When
        val result = MediaType.fromId(id)

        // Then
        assertEquals(MediaType.MOVIE_TV, result)
    }

    @Test
    fun `fromId should return correct MediaType for TV`() {
        // Given
        val id = 2

        // When
        val result = MediaType.fromId(id)

        // Then
        assertEquals(MediaType.TV, result)
    }

    @Test
    fun `fromId should return correct MediaType for EPISODE`() {
        // Given
        val id = 3

        // When
        val result = MediaType.fromId(id)

        // Then
        assertEquals(MediaType.EPISODE, result)
    }
}