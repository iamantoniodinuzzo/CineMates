package com.indisparte.movie_details.fragments

import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.common.WatchProvider
import com.indisparte.movie_data.BelongsToCollection
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
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
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
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
    private val goodMovieId2 = 6904
    private val badMovieId = 1
    private val collectionId = 8636


    private val fakeMovieDetailsWithCollection = MovieDetails(
        adult = false,
        backdropPath = null,
        belongsToCollection = BelongsToCollection(
            backdropPath = null,
            id = collectionId,
            name = "Rudolph Brady",
            posterPath = null
        ),
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
    private val fakeSecondMovieDetailsWithCollection = MovieDetails(
        adult = false,
        backdropPath = null,
        belongsToCollection = BelongsToCollection(
            backdropPath = null,
            id = collectionId,
            name = "Rudolph Brady",
            posterPath = null
        ),
        budget = 1897,
        genres = listOf(),
        homepage = "epicurei",
        id = goodMovieId2,
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
    private val fakeMovieDetailsWithoutCollection = MovieDetails(
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

    private val fakeSimilarMovies = listOf<Movie>(
        Movie(
            adult = false,
            id = 3955,
            popularity = 0.1,
            posterPath = null,
            releaseDate = null,
            title = "graeco",
            voteAverage = 2.3
        ),
        Movie(
            adult = false,
            id = 9079,
            popularity = 4.5,
            posterPath = null,
            releaseDate = null,
            title = "pericula",
            voteAverage = 6.7
        )
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

    private val fakeCollectionDetails = CollectionDetails(
        backdropPath = null,
        id = collectionId,
        name = "Katherine Collier",
        overview = null,
        parts = listOf(),
        posterPath = null
    )

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository()
        viewModel = MovieDetailsViewModel(fakeMovieRepository)
    }

    @Test
    fun `test successful movie details retrieval`() = runBlocking {
        // Given
        fakeMovieRepository.addMovieDetails(goodMovieId, fakeMovieDetailsWithCollection)
        fakeMovieRepository.addVideos(goodMovieId, fakeVideos)
        fakeMovieRepository.addWatchProviders(goodMovieId, fakeWatchProvider)
        fakeMovieRepository.addReleaseDates(goodMovieId, fakeReleaseDates)
        fakeMovieRepository.addBackdrops(goodMovieId, fakeBackdrops)
        fakeMovieRepository.addCrew(goodMovieId, fakeCrew)
        fakeMovieRepository.addCast(goodMovieId, fakeCast)

        // When
        viewModel.onDetailsFragmentReady(goodMovieId)

        // Then
        val movieInfo = viewModel.movieInfo.first()
        assertNotNull(movieInfo)
        assertTrue(movieInfo is Result.Success)

        val successResult = movieInfo as Result.Success
        assertEquals(fakeMovieDetailsWithCollection, successResult.data.movieDetails)
        // Add more assertions for other data if needed
    }

    @Test
    fun `test error in movie details retrieval`() = runBlocking {
        // Given
        fakeMovieRepository.setShouldEmitException(true)
        val exception = CineMatesExceptions.GenericException
        fakeMovieRepository.setExceptionToEmit(exception)

        // When
        viewModel.onDetailsFragmentReady(goodMovieId)

        // Then
        val movieInfo = viewModel.movieInfo.first()
        assertNotNull(movieInfo)
        assertTrue(movieInfo is Result.Error)

        val errorResult = movieInfo as Result.Error
        assertEquals(errorResult.exception, exception)
    }

    @Test
    fun testGetCollectionPartsSuccess() = runBlocking {
        //Given
        fakeMovieRepository.addMovieDetails(goodMovieId, fakeMovieDetailsWithCollection)
        fakeMovieRepository.addCollectionDetails(collectionId, fakeCollectionDetails)

        // When
        viewModel.onDetailsFragmentReady(goodMovieId)

        // Then
        val collectionParts = viewModel.collectionParts.first()
        assertNotNull(collectionParts)
        assertTrue(collectionParts is Result.Success)

        val successResult = collectionParts as Result.Success
        assertEquals(successResult, collectionParts)

    }

    @Test
    fun getCollection_movieWithNoCollection_success() = runBlocking{
        //GIVEN
        fakeMovieRepository.addMovieDetails(goodMovieId, fakeMovieDetailsWithoutCollection)

        //WHEN
        viewModel.onDetailsFragmentReady(goodMovieId)

        //THEN
        val collectionParts = viewModel.collectionParts.first()
        assertNull(collectionParts)

    }

    @Test
    fun testGetSimilarMoviesSuccess() = runBlocking {
        // Given
        fakeMovieRepository.addSimilarMovies(goodMovieId, fakeSimilarMovies)

        // When
        viewModel.onDetailsFragmentReady(goodMovieId)

        // Then
        val similarMovies = viewModel.similarMovies.first()
        assertNotNull(similarMovies)
        assertTrue(similarMovies is Result.Success)

        val successResult = similarMovies as Result.Success
        assertEquals(successResult, similarMovies)
    }

    @Test
    fun testGetCastSuccess() = runBlocking {
        // Given
        fakeMovieRepository.addCast(goodMovieId, fakeCast)

        // When
        viewModel.onDetailsFragmentReady(goodMovieId)

        // Then
        val cast = viewModel.cast.first()
        assertNotNull(cast)
        assertTrue(cast is Result.Success)

        val successResult = cast as Result.Success
        assertEquals(successResult, cast)
    }

    @Test
    fun `test update selected movie and load movie info`() = runBlocking {
        // Given
        fakeMovieRepository.addMovieDetails(goodMovieId, fakeMovieDetailsWithCollection)
        fakeMovieRepository.addVideos(goodMovieId, fakeVideos)
        fakeMovieRepository.addWatchProviders(goodMovieId, fakeWatchProvider)
        fakeMovieRepository.addReleaseDates(goodMovieId, fakeReleaseDates)
        fakeMovieRepository.addBackdrops(goodMovieId, fakeBackdrops)
        fakeMovieRepository.addCrew(goodMovieId, fakeCrew)
        fakeMovieRepository.addCast(goodMovieId, fakeCast)

        // When
        viewModel.onDetailsFragmentReady(goodMovieId)

        // Then
        val initialSelectedMovie = viewModel.selectedMovie.first()
        assertNotNull(initialSelectedMovie)
        assertTrue(initialSelectedMovie is Result.Success)
        assertEquals(goodMovieId, (initialSelectedMovie as Result.Success).data.id)

        // When
        fakeMovieRepository.addMovieDetails(goodMovieId2, fakeSecondMovieDetailsWithCollection)
        fakeMovieRepository.addVideos(goodMovieId2, fakeVideos)
        fakeMovieRepository.addWatchProviders(goodMovieId2, fakeWatchProvider)
        fakeMovieRepository.addReleaseDates(goodMovieId2, fakeReleaseDates)
        fakeMovieRepository.addBackdrops(goodMovieId2, fakeBackdrops)
        fakeMovieRepository.addCrew(goodMovieId2, fakeCrew)
        fakeMovieRepository.addCast(goodMovieId2, fakeCast)

        viewModel.onDetailsFragmentReady(goodMovieId2)

        // Then
        val updatedSelectedMovie = viewModel.selectedMovie.first()
        assertNotNull(updatedSelectedMovie)
        assertTrue(updatedSelectedMovie is Result.Success)
        assertEquals(goodMovieId2, (updatedSelectedMovie as Result.Success).data.id)

        // Verifica che tutti i dati di movieInfo siano caricati
        val movieInfo = viewModel.movieInfo.first()
        assertNotNull(movieInfo)
        assertTrue(movieInfo is Result.Success)
    }


    @After
    fun tearDown() {
        fakeMovieRepository.clearData()
    }
}