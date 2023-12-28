package com.indisparte.base

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class MediaTest {

    @Test
    fun `isToSee should be set to true and isSeen to false when isToSee is set to true`() {
        // Given
        val media = Media(
            id = 1,
            mediaName = "Test Media",
            voteAverage = 8.5,
            mediaType = MediaType.MOVIE,
            posterPath = null,
            popularity = 0.0
        )

        // When
        media.isToSee = true

        // Then
        assertTrue(media.isToSee)
        assertFalse(media.isSeen)
    }

    @Test
    fun `isSeen should be set to true and isToSee to false when isSeen is set to true`() {
        // Given
        val media = Media(
            id = 1,
            mediaName = "Test Media",
            voteAverage = 8.5,
            mediaType = MediaType.MOVIE,
            posterPath = null,
            popularity = 0.0
        )

        // When
        media.isSeen = true

        // Then
        assertTrue(media.isSeen)
        assertFalse(media.isToSee)
    }

    @Test
    fun `completePosterPathW780 should return correct complete image path`() {
        val posterPath = "/example_path.jpg"
        val expectedCompletePosterPath = TMDBItem.IMAGE_BASE_URL_W780 + posterPath
        // Given
        val media = Media(
            id = 4,
            mediaName = "Test Media",
            voteAverage = 8.0,
            mediaType = MediaType.MOVIE,
            posterPath = posterPath,
            popularity = 0.0
        )

        // When, Then
        assertEquals(expectedCompletePosterPath, media.completePosterPathW780)
    }

    @Test
    fun `completePosterPathW500 should return correct complete image path`() {
        val posterPath = "/example_path.jpg"
        val expectedCompletePosterPath = TMDBItem.IMAGE_BASE_URL_W500 + posterPath
        // Given
        val media = Media(
            id = 4,
            mediaName = "Test Media",
            voteAverage = 8.0,
            mediaType = MediaType.MOVIE,
            posterPath = posterPath,
            popularity = 0.0
        )

        // When, Then
        assertEquals(expectedCompletePosterPath, media.completePosterPathW500)
    }

    @Test
    fun `voteAverageAsString should return correct string representation`() {
        // Given
        val media =
            Media(id = 6, mediaName = "Test Media", voteAverage = 8.5, mediaType = MediaType.MOVIE, posterPath = null, popularity = 0.0)

        // When, Then
        assertEquals("8.5", media.voteAverageAsString)
    }

}