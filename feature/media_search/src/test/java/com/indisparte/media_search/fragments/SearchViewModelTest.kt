package com.indisparte.media_search.fragments

import com.indisparte.media_search.repository.fake.FakeMovieSearchRepository
import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import com.indisparte.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Antonio Di Nuzzo
 */
class SearchViewModelTest {

    @get:Rule
    val rule = MainDispatcherRule()

    private lateinit var viewModel: SearchViewModel
    private lateinit var fakeMovieSearchRepository: FakeMovieSearchRepository

    @Before
    fun setUp() {
        fakeMovieSearchRepository = FakeMovieSearchRepository()
        viewModel = SearchViewModel(fakeMovieSearchRepository)
    }

    @Test
    fun searchMovies_withResults_success() = runBlocking {
        // Given
        val query = "Harry Potter"
        val fakeResults = listOf(
            Movie(
                adult = false,
                id = 4854,
                popularity = 8.9,
                posterPath = null,
                releaseDate = null,
                title = "deseruisse",
                voteAverage = 10.11
            ),
            Movie(
                adult = false,
                id = 1640,
                popularity = 12.13,
                posterPath = null,
                releaseDate = null,
                title = "posse",
                voteAverage = 14.15
            )
        )
        fakeMovieSearchRepository.addSearchResults(query, fakeResults)

        // When
        viewModel.updateQuery(query)

        // Then
        val moviesResult = viewModel.moviesBySearch.first()
        assertTrue(moviesResult is Result.Success)
        assertEquals(fakeResults, (moviesResult as Result.Success).data)
    }

    @Test
    fun searchMovies_noResults_success() = runBlocking {
        // Given
        val query = "Nonexistent Movie"
        val fakeResults = emptyList<Movie>()
        fakeMovieSearchRepository.addSearchResults(query, fakeResults)

        // When
        viewModel.updateQuery(query)

        // Then
        val moviesResult = viewModel.moviesBySearch.first()
        assertTrue(moviesResult is Result.Success)
        assertEquals(fakeResults, (moviesResult as Result.Success).data)
    }

    @Test
    fun searchMovies_withError_error() = runBlocking {
        // Given
        val query = "Error Movie"
        val error = CineMatesExceptions.NoNetworkException
        fakeMovieSearchRepository.setShouldEmitException(true)
        fakeMovieSearchRepository.setExceptionToEmit(error)

        // When
        viewModel.updateQuery(query)

        // Then
        val moviesResult = viewModel.moviesBySearch.first()
        assertTrue(moviesResult is Result.Error)
        assertEquals(error, (moviesResult as Result.Error).exception)
    }


    @After
    fun tearDown() {
        fakeMovieSearchRepository.clearData()
    }
}