package com.indisparte.movie_data

import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */

class MovieCreditTest {

    @Test
    fun `credit should return character name if available`() {
        // Given
        val movieCredit = MovieCredit(
            adult = false,
            character = "John Doe",
            id = 1,
            popularity = 8.5,
            posterPath = "/poster.jpg",
            releaseDate = "2022-01-01",
            title = "Test Movie",
            voteAverage = 9.0,
            department = "Production"
        )

        // When
        val result = movieCredit.credit

        // Then
        assertEquals("John Doe", result)
    }

    @Test
    fun `credit should return department if character name is null`() {
        // Given
        val movieCredit = MovieCredit(
            adult = false,
            character = null,
            id = 2,
            popularity = 7.5,
            posterPath = "/another_poster.jpg",
            releaseDate = "2022-02-15",
            title = "Another Movie",
            voteAverage = 8.0,
            department = "Art Direction"
        )

        // When
        val result = movieCredit.credit

        // Then
        assertEquals("Art Direction", result)
    }

    @Test
    fun `year should return correct release year`() {
        // Given
        val movieCredit = MovieCredit(
            adult = true,
            character = "Jane Smith",
            id = 3,
            popularity = 9.5,
            posterPath = "/third_poster.jpg",
            releaseDate = "2021-03-20",
            title = "Third Movie",
            voteAverage = 8.5,
            department = "Sound"
        )

        // When
        val result = movieCredit.year

        // Then
        assertEquals("2021", result)
    }

    @Test
    fun `year should return null for null release date`() {
        // Given
        val movieCredit = MovieCredit(
            adult = false,
            character = "Bob Johnson",
            id = 4,
            popularity = 6.5,
            posterPath = "/fourth_poster.jpg",
            releaseDate = null,
            title = "Fourth Movie",
            voteAverage = 7.0,
            department = "Cinematography"
        )

        // When
        val result = movieCredit.year

        // Then
        assertEquals(null, result)
    }
}