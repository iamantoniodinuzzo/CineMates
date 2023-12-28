package com.indisparte.movie_data

import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */

class BelongsToCollectionTest {

    @Test
    fun `completePosterPathW780 should return complete URL of poster with width 780 pixels`() {
        // Given
        val collection = BelongsToCollection(
            backdropPath = "/backdrop.jpg",
            id = 1,
            name = "Test Collection",
            posterPath = "/poster.jpg"
        )

        // When
        val result = collection.completePosterPathW780

        // Then
        assertEquals("https://image.tmdb.org/t/p/w780/poster.jpg", result)
    }

    @Test
    fun `completePosterPathW780 should return null if poster path is null`() {
        // Given
        val collection = BelongsToCollection(
            backdropPath = "/another_backdrop.jpg",
            id = 2,
            name = "Another Collection",
            posterPath = null
        )

        // When
        val result = collection.completePosterPathW780

        // Then
        assertEquals(null, result)
    }

    @Test
    fun `completeBackdropPathW500 should return complete URL of backdrop with width 500 pixels`() {
        // Given
        val collection = BelongsToCollection(
            backdropPath = "/backdrop.jpg",
            id = 3,
            name = "Yet Another Collection",
            posterPath = "/poster.jpg"
        )

        // When
        val result = collection.completeBackdropPathW500

        // Then
        assertEquals("https://image.tmdb.org/t/p/w500/backdrop.jpg", result)
    }

    @Test
    fun `completeBackdropPathW500 should return null if backdrop path is null`() {
        // Given
        val collection = BelongsToCollection(
            backdropPath = null,
            id = 4,
            name = "Fourth Collection",
            posterPath = "/another_poster.jpg"
        )

        // When
        val result = collection.completeBackdropPathW500

        // Then
        assertEquals(null, result)
    }
}
