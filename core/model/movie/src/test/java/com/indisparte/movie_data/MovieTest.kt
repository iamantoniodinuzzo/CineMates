package com.indisparte.movie_data

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Antonio Di Nuzzo
 */
class MovieTest {

    @Test
    fun `formattedReleaseDate should return formatted release date`() {
        // Given
        val movie = Movie(
            adult = false,
            id = 1,
            popularity = 8.5,
            posterPath = "/poster.jpg",
            releaseDate = "2022-01-01",
            title = "Test Movie",
            voteAverage = 9.0
        )

        // When
        val result = movie.formattedReleaseDate

        // Then
        assertEquals("01 gennaio 2022", result)
    }

    @Test
    fun `formattedReleaseDate should return null for null release date`() {
        // Given
        val movie = Movie(
            adult = true,
            id = 2,
            popularity = 7.5,
            posterPath = "/another_poster.jpg",
            releaseDate = null,
            title = "Another Movie",
            voteAverage = 8.0
        )

        // When
        val result = movie.formattedReleaseDate

        // Then
        assertEquals(null, result)
    }

    @Test
    fun `toString should return correct string representation`() {
        // Given
        val movie = Movie(
            adult = false,
            id = 3,
            popularity = 9.5,
            posterPath = "/third_poster.jpg",
            releaseDate = "2022-02-15",
            title = "Third Movie",
            voteAverage = 8.5
        )

        // When
        val result = movie.toString()

        // Then
        assertEquals("Movie(adult=false, releaseDate='2022-02-15', title='Third Movie')", result)
    }

    @Test
    fun `toString should return correct string representation for movie with null release date`() {
        // Given
        val movie = Movie(
            adult = true,
            id = 4,
            popularity = 6.5,
            posterPath = "/fourth_poster.jpg",
            releaseDate = null,
            title = "Fourth Movie",
            voteAverage = 7.0
        )

        // When
        val result = movie.toString()

        // Then
        assertEquals("Movie(adult=true, releaseDate='null', title='Fourth Movie')", result)
    }
}