package com.indisparte.movie_details.fragments

import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Genre
import com.indisparte.common.Video
import com.indisparte.common.WatchProvider
import com.indisparte.genre.repository.fake.FakeGenreRepository
import com.indisparte.movie_data.BelongsToCollection
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDate
import com.indisparte.movie_data.ReleaseDatesByCountry
import com.indisparte.movie_data.findReleaseDateByCountry
import com.indisparte.movie_data.repository.fake.FakeMovieRepository
import com.indisparte.movie_details.uiState.MovieInfoUiState
import com.indisparte.network.util.Result
import com.indisparte.network.exception.CineMatesException
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import com.indisparte.testing.util.rule.MainDispatcherRule
import com.indisparte.testing.util.rule.TimberRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.Locale

/**
 * @author Antonio Di Nuzzo
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    @get:Rule
    val rule: TestRule = MainDispatcherRule()


    companion object {
        @get:ClassRule
        @JvmStatic
        var timberRule = TimberRule()
    }

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var fakeMovieRepository: FakeMovieRepository
    private lateinit var fakeGenreRepository: FakeGenreRepository
    private val goodMovieId = 4567
    private val goodMovieId2 = 6904
    private val badMovieId = 1
    private val collectionId = 8636
    private val country = Locale.getDefault().country
    private val latestCertification = "libris"


    private val fakeGenres = listOf(
        Genre(id = 4269, name = "Leila Faulkner", isFavorite = false),
        Genre(id = 3853, name = "Eve McCarty", isFavorite = false)
    )
    private val fakeReleaseDatesByCountry = listOf(
        ReleaseDatesByCountry(
            country = country,
            releaseDates = listOf(
                ReleaseDate(
                    certification = latestCertification,
                    releaseDate = "detraxit",
                    type = 3384
                )
            )
        ),
        ReleaseDatesByCountry(
            country = "Brunei", releaseDates = listOf(
                ReleaseDate(
                    certification = "neque", releaseDate = "sem", type = 9138
                )
            )
        )
    )
    private val releaseDates =
        fakeReleaseDatesByCountry.findReleaseDateByCountry(country) ?: emptyList()

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
        genres = fakeGenres,
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
        genres = fakeGenres,
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
        genres = fakeGenres,
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
        fakeGenreRepository = FakeGenreRepository()
        viewModel = MovieDetailsViewModel(fakeMovieRepository, fakeGenreRepository)
    }

    @Test
    fun `Given valid movie info, when fetching movie info, then result is success`() =
        runBlockingTest {
            // GIVEN
            val movieInfo = MovieInfoUiState(
                fakeMovieDetailsWithCollection,
                fakeVideos,
                fakeWatchProvider,
                releaseDates,
                latestCertification,
                fakeBackdrops,
                fakeCrew
            )


            fakeMovieRepository.addMovieDetails(goodMovieId, fakeMovieDetailsWithCollection)
            fakeMovieRepository.addVideos(goodMovieId, fakeVideos)
            fakeMovieRepository.addWatchProviders(goodMovieId, fakeWatchProvider)
            fakeMovieRepository.addReleaseDatesByCountry(goodMovieId, fakeReleaseDatesByCountry)
            fakeMovieRepository.addBackdrops(goodMovieId, fakeBackdrops)
            fakeMovieRepository.addCrew(goodMovieId, fakeCrew)

            // WHEN
            viewModel.onDetailsFragmentReady(goodMovieId)

            // Assicurati che tutte le operazioni asincrone siano completate prima di proseguire
            advanceUntilIdle()
            
            // THEN
            val movieInfoResult =
                viewModel.movieInfo.filterIsInstance<Result.Success<MovieInfoUiState>>().first()


            assertEquals(movieInfo, movieInfoResult.data)
        }

    @Test
    fun `Given a new selected movie, when updating selected movie, then movie info data changes`() =
        runBlockingTest {
            // GIVEN
            val initialMovieInfo = MovieInfoUiState(
                fakeMovieDetailsWithCollection,
                fakeVideos,
                fakeWatchProvider,
                releaseDates,
                latestCertification,
                fakeBackdrops,
                fakeCrew
            )

            //Initial movie details
            fakeMovieRepository.addMovieDetails(goodMovieId, fakeMovieDetailsWithCollection)
            fakeMovieRepository.addVideos(goodMovieId, fakeVideos)
            fakeMovieRepository.addWatchProviders(goodMovieId, fakeWatchProvider)
            fakeMovieRepository.addReleaseDatesByCountry(goodMovieId, fakeReleaseDatesByCountry)
            fakeMovieRepository.addBackdrops(goodMovieId, fakeBackdrops)
            fakeMovieRepository.addCrew(goodMovieId, fakeCrew)


            // WHEN
            viewModel.onDetailsFragmentReady(goodMovieId)

            // THEN
            val movieInfoResult =
                viewModel.movieInfo.filterIsInstance<Result.Success<MovieInfoUiState>>().first()

            // Assicurati che tutte le operazioni asincrone siano completate prima di proseguire
            advanceUntilIdle()

            assertEquals(initialMovieInfo, movieInfoResult.data)

            //GIVEN
            val newMovieInfo = MovieInfoUiState(
                fakeSecondMovieDetailsWithCollection,
                fakeVideos,
                fakeWatchProvider,
                releaseDates,
                latestCertification,
                fakeBackdrops,
                fakeCrew
            )

            //new movie details
            fakeMovieRepository.addMovieDetails(goodMovieId2, fakeSecondMovieDetailsWithCollection)
            fakeMovieRepository.addVideos(goodMovieId2, fakeVideos)
            fakeMovieRepository.addWatchProviders(goodMovieId2, fakeWatchProvider)
            fakeMovieRepository.addReleaseDatesByCountry(goodMovieId2, fakeReleaseDatesByCountry)
            fakeMovieRepository.addBackdrops(goodMovieId2, fakeBackdrops)
            fakeMovieRepository.addCrew(goodMovieId2, fakeCrew)

            // WHEN
            viewModel.onDetailsFragmentReady(goodMovieId2)

            // THEN
            val newMovieInfoResult =
                viewModel.movieInfo.filterIsInstance<Result.Success<MovieInfoUiState>>().first()

            // Assicurati che tutte le operazioni asincrone siano completate prima di proseguire
            advanceUntilIdle()

            assertEquals(newMovieInfo, newMovieInfoResult.data)


        }


    @Test
    fun `Given an error in any data fetch, when fetching movie info, then result is error`() =
        runBlockingTest {
// GIVEN
            val exception = CineMatesException.GenericException
            fakeMovieRepository.setShouldEmitException(true)
            fakeMovieRepository.setExceptionToEmit(exception)

// WHEN
            viewModel.onDetailsFragmentReady(goodMovieId)

// THEN
            val movieInfoResult = viewModel.movieInfo.value
            assertTrue(movieInfoResult is Result.Error)
            assertEquals(exception, (movieInfoResult as Result.Error).exception)
        }

    @Test
    fun `Given valid collection details, when fetching collection details, then result is success`() =
        runBlockingTest {
// GIVEN
            fakeMovieRepository.addMovieDetails(goodMovieId, fakeMovieDetailsWithCollection)
            fakeMovieRepository.addCollectionDetails(collectionId, fakeCollectionDetails)

// WHEN
            viewModel.onDetailsFragmentReady(goodMovieId)

// THEN
            val collectionDetailsResult = viewModel.collectionParts.value
            assertTrue(collectionDetailsResult is Result.Success)
            assertEquals(fakeCollectionDetails, (collectionDetailsResult as Result.Success).data)
        }

    @Test
    fun `Given a movie without, when fetching collection details, then result is success`() =
        runBlockingTest {
// GIVEN
            fakeMovieRepository.addMovieDetails(goodMovieId2, fakeMovieDetailsWithoutCollection)

// WHEN
            viewModel.onDetailsFragmentReady(goodMovieId2)

// THEN
            val collectionDetailsResult = viewModel.collectionParts.value
            assertNull(collectionDetailsResult)
        }


    @Test
    fun `Given valid similar movies, when fetching similar movies, then result is success`() =
        runBlockingTest {
// GIVEN
            fakeMovieRepository.addSimilarMovies(goodMovieId, fakeSimilarMovies)

// WHEN
            viewModel.onDetailsFragmentReady(goodMovieId)

// THEN
            val similarMoviesResult = viewModel.similarMovies.value
            assertTrue(similarMoviesResult is Result.Success)

            val successResult = similarMoviesResult as Result.Success
            assertEquals(fakeSimilarMovies, successResult.data)
        }

    @Test
    fun `Given an error, when fetching similar movies, then result is error`() = runBlockingTest {
// GIVEN
        val exception = CineMatesException.GenericException
        fakeMovieRepository.setShouldEmitException(true)
        fakeMovieRepository.setExceptionToEmit(exception)

// WHEN
        viewModel.onDetailsFragmentReady(goodMovieId)

// THEN
        val similarMoviesResult = viewModel.similarMovies.value
        assertTrue(similarMoviesResult is Result.Error)
        assertEquals(exception, (similarMoviesResult as Result.Error).exception)
    }

    @Test
    fun `Given valid cast, when fetching cast, then result is success`() = runBlockingTest {
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
    fun `Given an error, when fetching cast, then result is error`() = runBlockingTest {
// GIVEN
        val exception = CineMatesException.GenericException
        fakeMovieRepository.setShouldEmitException(true)
        fakeMovieRepository.setExceptionToEmit(exception)

// WHEN
        viewModel.onDetailsFragmentReady(goodMovieId)

// THEN
        val castResult = viewModel.cast.first()
        assertTrue(castResult is Result.Error)
        assertEquals(exception, (castResult as Result.Error).exception)
    }

    @Test
    fun `Given a genre, when updating genre, then genre is updated in repository`() =
        runBlockingTest {
            // GIVEN
            fakeGenreRepository.addMovieGenres(fakeGenres)
            val genreUpdated = fakeGenres.first()
            genreUpdated.isFavorite = true

            // WHEN
            viewModel.updateGenre(genreUpdated)

            // THEN
            val updatedGenreList = fakeGenreRepository.getMovieGenreList().first()
            assertTrue(updatedGenreList is Result.Success)
            updatedGenreList as Result.Success
            assertEquals(updatedGenreList.data.find { genreUpdated.id == it.id }, genreUpdated)

        }

    @Test
    fun `Given an error, when updating genre, then error is propagated`() = runBlockingTest {
        // GIVEN
        fakeGenreRepository.setShouldEmitException(true)
        val exception = CineMatesException.GenericException
        fakeGenreRepository.setExceptionToEmit(exception)

        // WHEN
        viewModel.updateGenre(Genre(id = 4269, name = "Leila Faulkner", isFavorite = false))

        // THEN
        val updatedGenreList = fakeGenreRepository.getMovieGenreList().first()
        assertTrue(updatedGenreList is Result.Error)
        assertEquals(exception, (updatedGenreList as Result.Error).exception)
    }

    @After
    fun tearDown() {
        fakeMovieRepository.clearData()
    }
}