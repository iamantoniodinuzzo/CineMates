package com.indisparte.actor.repository

import com.indisparte.actor.source.remote.PeopleRemoteDataSource
import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.MovieCredit
import com.indisparte.network.Result
import com.indisparte.person.Person
import com.indisparte.person.PersonDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class PeopleRepositoryImpl
@Inject
constructor(
    private val peopleRemoteDataSource: PeopleRemoteDataSource,
) : PeopleRepository {
    override fun getPersonDetails(personId: Int): Flow<Result<PersonDetails>> =
        peopleRemoteDataSource.getPersonDetails(personId)

    override fun getPopularPersons(): Flow<Result<List<Person>>> =
        peopleRemoteDataSource.getPopularPersons()

    override fun getTrendingPersons(timeWindow: TimeWindow): Flow<Result<List<Person>>> =
        peopleRemoteDataSource.getTrendingPersons(timeWindow)

    override fun getMovieCredits(personId: Int): Flow<Result<List<MovieCredit>>> =
        peopleRemoteDataSource.getMovieCredits(personId)
}