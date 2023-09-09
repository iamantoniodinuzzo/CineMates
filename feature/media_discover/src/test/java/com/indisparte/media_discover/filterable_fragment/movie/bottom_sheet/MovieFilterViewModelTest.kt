package com.indisparte.media_discover.filterable_fragment.movie.bottom_sheet

import com.indisparte.common.Genre
import com.indisparte.filter.MovieSortOptions
import com.indisparte.genre.repository.fake.FakeGenreRepository
import com.indisparte.network.Result
import com.indisparte.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Antonio Di Nuzzo
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MovieFilterViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainDispatcherRule()

    private lateinit var viewModel: MovieFilterViewModel
    private lateinit var fakeGenreRepository: FakeGenreRepository

    @Before
    fun setupViewModel() {
        fakeGenreRepository = FakeGenreRepository()

        viewModel = MovieFilterViewModel(fakeGenreRepository)
    }

    @Test
    fun `test update sort type`() = runBlockingTest {
        // Given
        val sortOption = MovieSortOptions.AscendingPopularity

        // When
        viewModel.setSortType(sortOption)

        // Then
        val uiState = viewModel.uiState.first()
        assertEquals(sortOption, uiState.sortType)
    }

    @Test
    fun `test select genre`() = runBlockingTest {
        // Given
        val genreId = 123
        val initialUiState = viewModel.uiState.first()
        assertNull(initialUiState.genresId)

        // When
        viewModel.selectGenre(genreId)

        // Then
        val updatedUiState = viewModel.uiState.first()
        assertTrue(updatedUiState.genresId?.contains(genreId) == true)
    }

    @Test
    fun `test deselect genre`() = runBlockingTest {
        // Given
        val genreId = 123
        viewModel.selectGenre(genreId)
        val initialUiState = viewModel.uiState.first()
        assertNotNull(initialUiState.genresId)

        // When
        viewModel.deselectGenre(genreId)

        // Then
        val updatedUiState = viewModel.uiState.first()
        assertTrue(updatedUiState.genresId?.contains(genreId) != true)
    }

    @Test
    fun `test reset filters`() = runBlockingTest {
        // Given
        viewModel.selectGenre(123)
        viewModel.setSortType(MovieSortOptions.DescendingPopularity)

        // When
        viewModel.resetFilters()

        // Then
        val uiState = viewModel.uiState.first()
        assertNull(uiState.genresId)
        assertEquals(MovieSortOptions.DescendingPopularity, uiState.sortType)
    }

    @Test
    fun `test apply filters`() = runBlockingTest {
        // Given
        val initialUiState = viewModel.uiState.first()
        assertFalse(initialUiState.applyAllFilters)

        // When
        viewModel.applyFilters()

        // Then
        val uiState = viewModel.uiState.first()
        assertTrue(uiState.applyAllFilters)
    }

    @Test
    fun `test loading movie genres`() = runBlockingTest {
        // Given
        val fakeGenres = listOf(Genre(1, "Action"), Genre(2, "Drama"))
        fakeGenreRepository.addMovieGenres(fakeGenres)

        // When
        // ViewModel is initialized in setupViewModel

        // Then
        val movieGenres = viewModel.movieGenres.first()
        val successResult = movieGenres as Result.Success
        assertEquals(fakeGenres, successResult.data)
    }

    @Test
    fun `test loading movie genres failure`() = runBlockingTest {
        // Given
        fakeGenreRepository.setShouldEmitException(true)

        // When
        // ViewModel is initialized in setupViewModel
        viewModel = MovieFilterViewModel(fakeGenreRepository)

        // Then
        val movieGenres = viewModel.movieGenres.first()
        assertTrue(movieGenres is Result.Error)
    }

    @After
    fun tearDown(){
        fakeGenreRepository.clearData()
    }
}
