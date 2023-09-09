package com.indisparte.media_discover.filterable_fragment.movie

import com.indisparte.discover.repository.fake.FakeMovieDiscoverRepository
import com.indisparte.filter.MediaDiscoverFilter
import com.indisparte.filter.MovieSortOptions
import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import com.indisparte.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * @author Antonio Di Nuzzo
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FilterableMovieFragmentViewModelTest {

    @get:Rule
    val mainCoroutineRule: TestRule = MainDispatcherRule()

    private lateinit var viewModel: FilterableMovieFragmentViewModel
    private lateinit var fakeDiscoverRepository: FakeMovieDiscoverRepository

    @Before
    fun setupViewModel() {
        fakeDiscoverRepository = FakeMovieDiscoverRepository()
        viewModel = FilterableMovieFragmentViewModel(fakeDiscoverRepository)
    }

    @Test
    fun `test update filter`() = runBlockingTest {
        // Given
        val mediaDiscoverFilter = MediaDiscoverFilter(
            sortBy = MovieSortOptions.AscendingPopularity,
            withGenresIds = setOf(1, 2, 3)
        )

        // When
        viewModel.updateFilter(mediaDiscoverFilter)

        // Then
        val selectedFilter = viewModel.selectedMovieFilter.first()
        assertEquals(mediaDiscoverFilter, selectedFilter)
    }

    @Test
    fun `test clear filter`() = runBlockingTest {
        // Given
        val initialFilter = MediaDiscoverFilter(
            sortBy = MovieSortOptions.AscendingPopularity,
            withGenresIds = setOf(1, 2, 3)
        )
        viewModel.updateFilter(initialFilter)

        // When
        viewModel.clearFilter()

        // Then
        val selectedFilter = viewModel.selectedMovieFilter.first()
        assertEquals(MediaDiscoverFilter(), selectedFilter)
    }

    @Test
    fun `test filtered films`() = runBlockingTest {
        // Given
        val mediaDiscoverFilter = MediaDiscoverFilter(
            sortBy = MovieSortOptions.AscendingPopularity,
            withGenresIds = setOf(1, 2, 3)
        )
        val fakeMovies = listOf(
            Movie(
                adult = false,
                id = 6428,
                popularity = 16.17,
                posterPath = null,
                releaseDate = null,
                title = "possit",
                voteAverage = 18.19
            ),
            Movie(
                adult = false,
                id = 9855,
                popularity = 20.21,
                posterPath = null,
                releaseDate = null,
                title = "bibendum",
                voteAverage = 22.23
            )
        )
        fakeDiscoverRepository.addMovieResults(mediaDiscoverFilter, fakeMovies)

        // When
        viewModel.updateFilter(mediaDiscoverFilter)

        // Then
        val filteredFilms = viewModel.filteredFilms.first()
        assertTrue(filteredFilms is Result.Success)
        assertEquals(fakeMovies, (filteredFilms as Result.Success).data)
    }

    @Test
    fun `test loading movies with exception`() = runBlockingTest {
        // Given
        val mediaDiscoverFilter = MediaDiscoverFilter(
            sortBy = MovieSortOptions.AscendingPopularity,
            withGenresIds = setOf(1, 2, 3)
        )
        fakeDiscoverRepository.setShouldEmitException(true)

        // When
        viewModel.updateFilter(mediaDiscoverFilter)

        // Then
        val filteredFilms = viewModel.filteredFilms.first()
        assertTrue(filteredFilms is Result.Error)
    }
}