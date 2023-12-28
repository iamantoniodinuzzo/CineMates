package com.indisparte.movie_data

import com.indisparte.common.Genre
import com.indisparte.common.ProductionCompany
import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * @author Antonio Di Nuzzo
 */
class MovieDetailsTest {

    @Test
    fun `updateGenres should update genres with updated information`() {
        // Given
        val originalGenres = listOf(
            Genre(1, "Action", false),
            Genre(2, "Drama", false),
            Genre(3, "Comedy", false)
        )

        val updatedGenres = listOf(
            Genre(1, "Action", true),
            Genre(2, "Drama", false),
            Genre(3, "Comedy", true)
        )

        val movieDetails = MovieDetails(
            adult = false,
            backdropPath = "/backdrop.jpg",
            belongsToCollection = BelongsToCollection(
                backdropPath = null,
                id = 6758,
                name = "Bill Short",
                posterPath = null
            ),
            budget = 1_000_000_000,
            genres = originalGenres,
            homepage = "https://example.com",
            id = 123,
            originalLanguage = "en",
            originalTitle = "Test Movie",
            overview = "This is a test movie.",
            popularity = 8.5,
            posterPath = "/poster.jpg",
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            releaseDate = "2022-01-01",
            revenue = 800_000_000,
            runtime = 150,
            spokenLanguages = emptyList(),
            status = "Released",
            tagline = "A great movie",
            title = "Test Movie",
            video = false,
            voteAverage = 9.0,
            voteCount = 100
        )

        // When
        movieDetails.updateGenres(updatedGenres)

        // Then
        assertEquals(true, originalGenres[0].isFavorite)
        assertEquals(false, originalGenres[1].isFavorite)
        assertEquals(true, originalGenres[2].isFavorite)
    }

    @Test
    fun `formattedBudget should return formatted budget`() {
        // Given
        val movieDetails = MovieDetails(
            adult = false,
            backdropPath = "/backdrop.jpg",
            belongsToCollection = BelongsToCollection(
                backdropPath = null,
                id = 7012,
                name = "Jake Mayer",
                posterPath = null
            ),
            budget = 1_500_000_000,
            genres = emptyList(),
            homepage = "https://example.com",
            id = 456,
            originalLanguage = "en",
            originalTitle = "Test Movie",
            overview = "This is a test movie.",
            popularity = 7.5,
            posterPath = "/poster.jpg",
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            releaseDate = "2022-01-01",
            revenue = 800_000_000,
            runtime = 120,
            spokenLanguages = emptyList(),
            status = "Released",
            tagline = "A great movie",
            title = "Test Movie",
            video = true,
            voteAverage = 8.0,
            voteCount = 200
        )

        // When
        val result = movieDetails.formattedBudget

        // Then
        assertEquals("1,5 mld", result)
    }

    // Add similar tests for other formatted properties (formattedRevenue, formattedRuntime, etc.)

    @Test
    fun `completeBackdropPathW780 should return complete URL to backdrop image in W780 resolution`() {
        // Given
        val movieDetails = MovieDetails(
            adult = false,
            backdropPath = "/backdrop.jpg",
            belongsToCollection = BelongsToCollection(
                backdropPath = null,
                id = 3131,
                name = "Randall Nieves",
                posterPath = null
            ),
            budget = 1_000_000_000,
            genres = emptyList(),
            homepage = "https://example.com",
            id = 789,
            originalLanguage = "en",
            originalTitle = "Test Movie",
            overview = "This is a test movie.",
            popularity = 9.5,
            posterPath = "/poster.jpg",
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            releaseDate = "2022-01-01",
            revenue = 900_000_000,
            runtime = 130,
            spokenLanguages = emptyList(),
            status = "Released",
            tagline = "A great movie",
            title = "Test Movie",
            video = false,
            voteAverage = 9.0,
            voteCount = 300
        )

        // When
        val result = movieDetails.completeBackdropPathW780

        // Then
        assertEquals("https://image.tmdb.org/t/p/w780/backdrop.jpg", result)
    }

    //TODO: Add similar tests for other complete image paths (completeBackdropPathW500, etc.)

    @Test
    fun `productionCompaniesName should return concatenated string of production company names`() {
        // Given
        val productionCompanies = listOf(
            ProductionCompany(1, "Company A", "Logo A"),
            ProductionCompany(2, "Company B", "Logo B"),
            ProductionCompany(3, "Company C", "Logo C")
        )

        val movieDetails = MovieDetails(
            adult = false,
            backdropPath = "/backdrop.jpg",
            belongsToCollection = BelongsToCollection(
                backdropPath = null,
                id = 1565,
                name = "Kathleen Moreno",
                posterPath = null
            ),
            budget = 1_000_000_000,
            genres = emptyList(),
            homepage = "https://example.com",
            id = 101,
            originalLanguage = "en",
            originalTitle = "Test Movie",
            overview = "This is a test movie.",
            popularity = 8.0,
            posterPath = "/poster.jpg",
            productionCompanies = productionCompanies,
            productionCountries = emptyList(),
            releaseDate = "2022-01-01",
            revenue = 700_000_000,
            runtime = 110,
            spokenLanguages = emptyList(),
            status = "Released",
            tagline = "A great movie",
            title = "Test Movie",
            video = true,
            voteAverage = 7.5,
            voteCount = 400
        )

        // When
        val result = movieDetails.productionCompaniesName

        // Then
        assertEquals("Logo A, Logo B, Logo C", result)
    }
}