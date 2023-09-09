package com.indisparte.movie_details.fragments

import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.common.WatchProvider
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDate
import com.indisparte.movie_data.ReleaseDatesByCountry
import com.indisparte.movie_data.repository.fake.FakeMovieRepository
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import com.indisparte.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * @author Antonio Di Nuzzo
 */
class MovieDetailsViewModelTest {
    @get:Rule
    val rule: TestRule = MainDispatcherRule()

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var fakeMovieRepository: FakeMovieRepository

    private val goodMovieId = 4567
    private val badMovieId = 1

    private val fakeMovieDetails = MovieDetails(
        adult = false,
        backdropPath = null,
        belongsToCollection = null,
        budget = 1897,
        genres = listOf(),
        homepage = "epicurei",
        id = goodMovieId,
        originalLanguage = "elementum",
        originalTitle = "theophrastus",
        overview = "viris",
        popularity = 14.15,
        posterPath = null,
        productionCompanies = listOf(),
        productionCountries = listOf(),
        releaseDate = "patrioque",
        revenue = 9227,
        runtime = 5557,
        spokenLanguages = listOf(),
        status = "necessitatibus",
        tagline = "no",
        title = "agam",
        video = true,
        voteAverage = 16.17,
        voteCount = 9468
    )
    private val fakeVideos = listOf<Video>(
        Video(
            id = "comprehensam",
            key = "posuere",
            name = "Israel Schmidt",
            official = false,
            site = "eros",
            type = "orci"
        )
    )
    private val fakeWatchProvider = CountryResult(
        link = "suavitate",
        flatrate = listOf(
            WatchProvider(
                displayPriority = 2708,
                logoPath = null,
                providerName = "Susanne Ford"
            )
        ),
        rent = listOf(
            WatchProvider(
                displayPriority = 7799,
                logoPath = null,
                providerName = "Ellen Meyer"
            )
        ),
        buy = listOf(
            WatchProvider(
                displayPriority = 3366,
                logoPath = null,
                providerName = "Josefa Rodgers"
            )
        ),
        free = listOf(
            WatchProvider(
                displayPriority = 1142,
                logoPath = null,
                providerName = "Theron Johnston"
            )
        )
    )
    private val fakeReleaseDates = listOf(
        ReleaseDatesByCountry(
            country = "Albania",
            releaseDates = listOf(
                ReleaseDate(
                    certification = "libris",
                    releaseDate = "detraxit",
                    type = 3384
                )
            )
        )
    )
    private val fakeBackdrops =
        listOf(Backdrop(filePath = "similique", height = 7930, width = 8620))
    private val fakeCrew = listOf(
        Crew(
            adult = false,
            creditId = "sumo",
            department = "nam",
            gender = 8825,
            id = 4753,
            job = "deserunt",
            knownForDepartment = "mattis",
            name = "Kenneth Pearson",
            originalName = "Marcie Beach",
            popularity = 18.19,
            profilePath = null
        )
    )
    private val fakeCast = listOf(
        Cast(
            adult = false,
            castId = 8633,
            character = "consul",
            creditId = "ullamcorper",
            gender = 3345,
            id = 3861,
            knownForDepartment = "vituperatoribus",
            name = "Marsha Griffith",
            order = 6453,
            originalName = "Patricia Stone",
            popularity = 20.21,
            profilePath = null
        )
    )

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository()
        viewModel = MovieDetailsViewModel(fakeMovieRepository)
    }

    @Test
    fun `test successful movie details retrieval`() = runTest {
        // Arrange
        fakeMovieRepository.addMovieDetails(goodMovieId, fakeMovieDetails)
        fakeMovieRepository.addVideos(goodMovieId, fakeVideos)
        fakeMovieRepository.addWatchProviders(goodMovieId, fakeWatchProvider)
        fakeMovieRepository.addReleaseDates(goodMovieId, fakeReleaseDates)
        fakeMovieRepository.addBackdrops(goodMovieId, fakeBackdrops)
        fakeMovieRepository.addCrew(goodMovieId, fakeCrew)
        fakeMovieRepository.addCast(goodMovieId, fakeCast)

        // Act
        viewModel.onDetailsFragmentReady(goodMovieId)

        // Assert
        val movieInfo = viewModel.movieInfo.first()
        assertNotNull(movieInfo)
        assertTrue(movieInfo is Result.Success)

        val successResult = movieInfo as Result.Success
        assertEquals(fakeMovieDetails, successResult.data.movieDetails)
        // Add more assertions for other data if needed
    }

    @Test
    fun `test error in movie details retrieval`() = runBlocking {
        // Arrange
        fakeMovieRepository.setShouldEmitException(true)
        val exception = CineMatesExceptions.GenericException
        fakeMovieRepository.setExceptionToEmit(exception)

        // Act
        viewModel.onDetailsFragmentReady(goodMovieId)

        // Assert
        // Wait for movieInfo to transition from Result.Loading to Error
        val movieInfo = viewModel.movieInfo.value
        if (movieInfo is Result.Loading) {
            viewModel.movieInfo.collect {
                if (it is Result.Error) {
                    // Assert
                    assertNotNull(it)
                    assertEquals(exception, it.exception)
                }
            }
        } else {
            // If movieInfo is not in Loading state, fail the test
            fail("Expected movieInfo to be in Loading state, but it's in ${movieInfo?.javaClass?.simpleName}")
        }

    }



    @After
    fun tearDown() {
        fakeMovieRepository.clearData()
    }
}