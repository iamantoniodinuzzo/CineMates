package com.indisparte.movie_data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class ReleaseDateTest {


    @Test
    fun `formattedReleaseDate should return correct formatted date`() {
        // Given
        val releaseDate = ReleaseDate(
            certification = "PG-13",
            releaseDate = "2022-02-01T20:45:00Z",
            type = 3
        )

        // When
        val result = releaseDate.formattedReleaseDate

        // Then
        assertEquals("01 febbraio 2022", result)
    }

    @Test
    fun `formattedReleaseDate should return null for null release date`() {
        // Given
        val releaseDate = ReleaseDate(
            certification = "R",
            releaseDate = "",
            type = 2
        )

        // When
        val result = releaseDate.formattedReleaseDate

        // Then
        assertNull(result)
    }

    @Test
    fun `getLatestReleaseCertification should return correct certification for a list of release dates`() {
        // Given
        val releaseDates = listOf(
            ReleaseDate("PG", "2022-01-01T12:00:00Z", 1),
            ReleaseDate("PG-13", "2022-01-15T18:30:00Z", 2),
            ReleaseDate("R", "2022-02-01T20:45:00Z", 3)
        )

        // When
        val result = releaseDates.getLatestReleaseCertification()

        // Then
        assertEquals("R", result)
    }

    @Test
    fun `getLatestReleaseCertification should return empty string for an empty list`() {
        // Given
        val emptyReleaseDates: List<ReleaseDate> = emptyList()

        // When
        val result = emptyReleaseDates.getLatestReleaseCertification()

        // Then
        assertEquals("", result)
    }

    @Test
    fun `getLatestReleaseCertification should return correct certification for a list with one release date`() {
        // Given
        val singleReleaseDate = listOf(
            ReleaseDate("PG", "2022-01-01T12:00:00Z", 1)
        )

        // When
        val result = singleReleaseDate.getLatestReleaseCertification()

        // Then
        assertEquals("PG", result)
    }

    @Test
    fun `getLatestReleaseCertification should return empty string for an empty list of release dates`() {
        // Given
        val releaseDates = emptyList<ReleaseDate>()

        // When
        val result = releaseDates.getLatestReleaseCertification()

        // Then
        assertEquals("", result)
    }

    //TODO: Add more tests as needed for other scenarios
}
