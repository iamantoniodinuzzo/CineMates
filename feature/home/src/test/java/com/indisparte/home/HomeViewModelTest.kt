package com.indisparte.home

import com.google.common.truth.Truth.assertThat
import com.indisparte.actor.repository.fake.FakePeopleRepository
import com.indisparte.genre.repository.fake.FakeGenreRepository
import com.indisparte.home.util.Section
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.repository.fake.FakeMovieRepository
import com.indisparte.network.util.Result
import com.indisparte.network.exception.CineMatesException
import com.indisparte.person.Person
import com.indisparte.testing.util.rule.MainDispatcherRule
import com.indisparte.tv.TvShow
import com.indisparte.tv.repository.fake.FakeTvRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule



/**
 * @author Antonio Di Nuzzo
 */
class HomeViewModelTest {

    @get:Rule
    val rule: TestRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var movieRepository: FakeMovieRepository
    private lateinit var tvRepository: FakeTvRepository
    private lateinit var peopleRepository: FakePeopleRepository
    private lateinit var genreRepository: FakeGenreRepository

    @Before
    fun setup() {
        movieRepository = FakeMovieRepository()
        tvRepository = FakeTvRepository()
        peopleRepository = FakePeopleRepository()
        genreRepository = FakeGenreRepository()
        viewModel = HomeViewModel(movieRepository, tvRepository, peopleRepository, genreRepository)
    }

    @Test
    fun `fetchData should update sections correctly`() = runBlocking {
        val fakeMovies = listOf(
            Movie(
                adult = false,
                id = 5512,
                popularity = 0.1,
                posterPath = null,
                releaseDate = null,
                title = "proin",
                voteAverage = 2.3
            ),
            Movie(
                adult = false,
                id = 1740,
                popularity = 4.5,
                posterPath = null,
                releaseDate = null,
                title = "natoque",
                voteAverage = 6.7
            )
        )
        val fakeTvShows = listOf<TvShow>(
            TvShow(
                id = 4257,
                name = "Janis Pena",
                popularity = 8.9,
                posterPath = null,
                voteAverage = 10.11
            )
        )
        val fakePeople = listOf<Person>(
            Person(
                adult = false,
                gender = 1643,
                id = 7736,
                knownForDepartment = "atomorum",
                name = "Fletcher Burton",
                popularity = 12.13,
                profilePath = null
            )
        )

        movieRepository.addMovies(fakeMovies)
        tvRepository.addTvShows(fakeTvShows)
        peopleRepository.addPopularPersons(fakePeople)

        val expectedSections = listOf(
            Section.MovieSection(R.string.section_popular_movie, Result.Success(fakeMovies)),
            Section.PeopleSection(R.string.section_popular_people, Result.Success(fakePeople)),
            Section.TvShowSection(R.string.section_popular_tv, Result.Success(fakeTvShows))
        )

        // Act
        viewModel.fetchData()

        // Assert
        val sections = viewModel.sections.value
        assertThat(sections).containsExactlyElementsIn(expectedSections).inOrder()

    }

    @Test
    fun `fetchData should handle repository error`() = runBlocking {
        // Arrange
        movieRepository.setShouldEmitException(true)
        tvRepository.setShouldEmitException(true)
        peopleRepository.setShouldEmitException(true)
        val expectedSections = listOf(
            Section.MovieSection(
                R.string.section_popular_movie,
                Result.Error(CineMatesException.GenericException)
            ),
            Section.PeopleSection(
                R.string.section_popular_people,
                Result.Error(CineMatesException.GenericException)
            ),
            Section.TvShowSection(
                R.string.section_popular_tv,
                Result.Error(CineMatesException.GenericException)
            )
        )
        // Act
        viewModel.fetchData()

        // Assert
        val sections = viewModel.sections.value
        assertThat(sections).containsExactlyElementsIn(expectedSections).inOrder()
    }


    @After
    fun tearDown() {
        movieRepository.clearData()
        tvRepository.clearData()
        peopleRepository.clearData()
    }
}