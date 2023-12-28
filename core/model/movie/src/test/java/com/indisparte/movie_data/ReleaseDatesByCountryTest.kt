package com.indisparte.movie_data

import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class ReleaseDatesByCountryTest {

    @Test
    fun `findReleaseDateByCountry should return release dates for a valid country`() {
        // Given
        val releaseDatesByCountryList = listOf(
            ReleaseDatesByCountry(
                country = "USA",
                releaseDates = listOf(
                    ReleaseDate("PG-13", "2022-01-01T12:00:00Z", 3),
                    ReleaseDate("R", "2022-01-15T18:30:00Z", 3),
                    ReleaseDate("PG", "2022-02-01T20:45:00Z", 3)
                )
            ),
            ReleaseDatesByCountry(
                country = "UK",
                releaseDates = listOf(
                    ReleaseDate("PG", "2022-01-05T14:00:00Z", 3),
                    ReleaseDate("12A", "2022-02-10T22:30:00Z", 3),
                )
            )
        )

        // When
        val result = releaseDatesByCountryList.findReleaseDateByCountry("usa")

        // Then
        assertEquals(
            listOf(
                ReleaseDate("PG-13", "2022-01-01T12:00:00Z", 3),
                ReleaseDate("R", "2022-01-15T18:30:00Z", 3),
                ReleaseDate("PG", "2022-02-01T20:45:00Z", 3)
            ),
            result
        )
    }

    @Test
    fun `findReleaseDateByCountry should return null for an invalid country`() {
        // Given
        val releaseDatesByCountryList = listOf(
            ReleaseDatesByCountry(
                country = "USA",
                releaseDates = listOf(
                    ReleaseDate("PG-13", "2022-01-01T12:00:00Z", 3),
                    ReleaseDate("R", "2022-01-15T18:30:00Z", 3),
                    ReleaseDate("PG", "2022-02-01T20:45:00Z", 3)
                )
            ),
            ReleaseDatesByCountry(
                country = "UK",
                releaseDates = listOf(
                    ReleaseDate("PG", "2022-01-05T14:00:00Z", 3),
                    ReleaseDate("12A", "2022-02-10T22:30:00Z", 3),
                )
            )
        )

        // When
        val result = releaseDatesByCountryList.findReleaseDateByCountry("Germany")

        // Then
        assertEquals(null, result)
    }
}