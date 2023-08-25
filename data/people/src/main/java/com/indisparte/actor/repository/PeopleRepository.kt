package com.indisparte.actor.repository

import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.MovieCredit
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.PersonDetails
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface PeopleRepository {
    fun getPersonDetails(personId: Int): Flow<Result<PersonDetails>>
    fun getPopularPersons(): Flow<Result<List<Person>>>
    fun getTrendingPersons(timeWindow: TimeWindow): Flow<Result<List<Person>>>

    fun getMovieCredits(personId: Int): Flow<Result<MovieCredit>>
}