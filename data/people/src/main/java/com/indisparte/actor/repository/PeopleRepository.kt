package com.indisparte.actor.repository

import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.MovieCredit
import com.indisparte.person.Person
import com.indisparte.person.PersonDetails
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
interface PeopleRepository {
    fun getPersonDetails(personId: Int): Flow<Result<PersonDetails>>
    fun getPopularPersons(): Flow<Result<List<Person>>>
    fun getTrendingPersons(timeWindow: TimeWindow): Flow<Result<List<Person>>>

    fun getMovieCredits(personId: Int): Flow<Result<List<MovieCredit>>>
}