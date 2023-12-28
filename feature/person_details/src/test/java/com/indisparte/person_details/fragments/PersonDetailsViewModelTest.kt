package com.indisparte.person_details.fragments

import com.google.common.truth.Truth.assertThat
import com.indisparte.actor.repository.fake.FakePeopleRepository
import com.indisparte.movie_data.MovieCredit
import com.indisparte.network.util.Result
import com.indisparte.person.PersonDetails
import com.indisparte.testing.util.rule.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * @author Antonio Di Nuzzo
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PersonDetailsViewModelTest {

    @get:Rule
    val rule: TestRule = MainDispatcherRule()
    private lateinit var viewModel: PersonDetailsViewModel
    private lateinit var fakeRepository: FakePeopleRepository

    @Before
    fun setUp() {
        fakeRepository = FakePeopleRepository()
        viewModel = PersonDetailsViewModel(fakeRepository)
    }

    @After
    fun tearDown(){
        fakeRepository.clearData()
    }

    @Test
    fun `test getPersonDetails success`() =
        runBlockingTest {
            val personId = 1
            val personDetails = PersonDetails(
                adult = false,
                alsoKnownAs = listOf(),
                biography = "gravida",
                birthday = null,
                deathDay = null,
                gender = 6046,
                homepage = null,
                id = 7732,
                imdbId = "percipit",
                knownForDepartment = "mi",
                name = "Davis Perkins",
                placeOfBirth = null,
                popularity = 0.1,
                profilePath = null,
                images = listOf()
            )
            fakeRepository.addPersonDetails(personId, personDetails)

            viewModel.getPersonDetailsAndCredits(personId)
            val result = viewModel.personDetails.first()

            assertThat(result).isInstanceOf(Result.Success::class.java)
            assertThat((result as Result.Success).data).isEqualTo(personDetails)
        }

    @Test
    fun `test getPersonDetails error`() =
        runBlockingTest {
            val personId = 1
            fakeRepository.setShouldEmitException(true)

            viewModel.getPersonDetailsAndCredits(personId)
            val result = viewModel.personDetails.first()

            assertThat(result).isInstanceOf(Result.Error::class.java)
        }


    @Test
    fun `test getMovieCredits success`() =
        runBlockingTest {
            val personId = 1
            val movieCredits = listOf(
                MovieCredit(
                    adult = false,
                    character = null,
                    id = 5913,
                    popularity = 2.3,
                    posterPath = null,
                    releaseDate = null,
                    title = "sapientem",
                    voteAverage = 4.5,
                    department = null
                ),
                MovieCredit(
                    adult = false,
                    character = null,
                    id = 1469,
                    popularity = 6.7,
                    posterPath = null,
                    releaseDate = null,
                    title = "nullam",
                    voteAverage = 8.9,
                    department = null
                )
            )
            fakeRepository.addMovieCredits(personId, movieCredits)

            viewModel.getPersonDetailsAndCredits(personId)
            val result = viewModel.movieCredits.first()

            assertThat(result).isInstanceOf(Result.Success::class.java)
            assertThat((result as Result.Success).data).isEqualTo(movieCredits)
        }

    @Test
    fun `test getMovieCredits error`() =
        runBlockingTest {
            val personId = 1
            fakeRepository.setShouldEmitException(true)

            viewModel.getPersonDetailsAndCredits(personId)
            val result = viewModel.movieCredits.first()

            assertThat(result).isInstanceOf(Result.Error::class.java)
            // You can add more assertions for the specific error scenario if needed
        }
}