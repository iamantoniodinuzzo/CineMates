package com.indisparte.media_search.fragments

import com.indisparte.media_search.repository.fake.FakeMediaSearchRepository
import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import com.indisparte.testing.util.rule.MainDispatcherRule
import com.indisparte.tv.TvShow
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Antonio Di Nuzzo
 */
@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val rule = MainDispatcherRule()

    private lateinit var viewModel: SearchViewModel
    private lateinit var fakeMediaSearchRepository: FakeMediaSearchRepository

    @Before
    fun setUp() {
        fakeMediaSearchRepository = FakeMediaSearchRepository()
        viewModel = SearchViewModel(fakeMediaSearchRepository)
    }


    @Test
    fun `Given non-empty query, when searching movies successfully, then moviesBySearch contains success data`() =
        runBlockingTest {
            // GIVEN
            val query = "MovieTitle"
            val fakeMovies = listOf(
                Movie(
                    adult = false,
                    id = 1616,
                    popularity = 0.1,
                    posterPath = null,
                    releaseDate = null,
                    title = "movet",
                    voteAverage = 2.3
                ),
                Movie(
                    adult = false,
                    id = 5092,
                    popularity = 4.5,
                    posterPath = null,
                    releaseDate = null,
                    title = "integer",
                    voteAverage = 6.7
                )
            )
            fakeMediaSearchRepository.addMovieSearchResults(query, fakeMovies)

            // WHEN
            viewModel.updateQuery(query)

            // THEN
            val moviesBySearch = viewModel.moviesBySearch.value
            assertTrue(moviesBySearch is Result.Success)
            assertEquals(fakeMovies, (moviesBySearch as Result.Success).data)
        }

    @Test
    fun `Given non-empty query, when searching tv show successfully, then tvBySearch contains success data`() =
        runBlockingTest {
            // GIVEN
            val query = "TvTitle"
            val fakeTvShows = listOf<TvShow>(
                TvShow(
                    id = 6262,
                    name = "Felix Howard",
                    popularity = 0.1,
                    posterPath = null,
                    voteAverage = 2.3
                ),
                TvShow(
                    id = 1412,
                    name = "Cornell Paul",
                    popularity = 4.5,
                    posterPath = null,
                    voteAverage = 6.7
                )
            )
            fakeMediaSearchRepository.addTvSearchResults(query, fakeTvShows)

            // WHEN
            viewModel.updateQuery(query)

            // THEN
            val tvShowResult = viewModel.tvShowBySearch.value
            assertTrue(tvShowResult is Result.Success)
            assertEquals(fakeTvShows, (tvShowResult as Result.Success).data)
        }

    @Test
    fun `Given empty query, when updating query, then moviesBySearch contains empty success data`() =
        runBlockingTest {
            // GIVEN
            val query = ""

            // WHEN
            viewModel.updateQuery(query)

            // THEN
            val moviesBySearch = viewModel.moviesBySearch.value
            val tvBySearch = viewModel.tvShowBySearch.value
            assertTrue(moviesBySearch is Result.Success)
            assertTrue(tvBySearch is Result.Success)
            assertEquals(emptyList<Movie>(), (moviesBySearch as Result.Success).data)
            assertEquals(emptyList<TvShow>(), (tvBySearch as Result.Success).data)
        }

    @Test
    fun `Given non-empty query, when searching movies with error, then moviesBySearch contains error result`() =
        runBlockingTest {
            // GIVEN
            val query = "MovieTitle"
            fakeMediaSearchRepository.setShouldEmitException(true)

            // WHEN
            viewModel.updateQuery(query)

            // THEN
            val moviesBySearch = viewModel.moviesBySearch.value
            assertTrue(moviesBySearch is Result.Error)
        }

    @Test
    fun `Given non-empty query, when searching tv with error, then tvBySearch contains error result`() =
        runBlockingTest {
            // GIVEN
            val query = "TvTitle"
            fakeMediaSearchRepository.setShouldEmitException(true)

            // WHEN
            viewModel.updateQuery(query)

            // THEN
            val tvBySearch = viewModel.tvShowBySearch.value
            assertTrue(tvBySearch is Result.Error)
        }

    @Test
    fun `Given non-empty query, when searching movies with exception, then moviesBySearch contains error result`() =
        runBlockingTest {
            // GIVEN
            val query = "MovieTitle"
            val exception = CineMatesExceptions.GenericException
            fakeMediaSearchRepository.setExceptionToEmit(exception)

            // WHEN
            viewModel.updateQuery(query)

            // THEN
            val moviesBySearch = viewModel.moviesBySearch.value


            assertTrue(moviesBySearch is Result.Error)
            assertEquals(exception, (moviesBySearch as Result.Error).exception)
        }

    @Test
    fun `Given non-empty query, when searching tv with exception, then tvBySearch contains error result`() =
        runBlockingTest {
            // GIVEN
            val query = "TvTitle"
            val exception = CineMatesExceptions.GenericException
            fakeMediaSearchRepository.setExceptionToEmit(exception)

            // WHEN
            viewModel.updateQuery(query)

            // THEN
            val tvBySearch = viewModel.tvShowBySearch.value


            assertTrue(tvBySearch is Result.Error)
            assertEquals(exception, (tvBySearch as Result.Error).exception)
        }


    @After
    fun tearDown() {
        fakeMediaSearchRepository.clearData()
    }
}