package com.indisparte.actor.repository

import com.indisparte.actor.mapper.mapToMovieCredit
import com.indisparte.actor.mapper.mapToPerson
import com.indisparte.actor.mapper.mapToPersonDetails
import com.indisparte.actor.source.PeopleDataSource
import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.MovieCredit
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.PersonDetails
import com.indisparte.network.Result
import com.indisparte.network.getListFromResponse
import com.indisparte.network.getSingleFromResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class PeopleRepositoryImpl
@Inject
constructor(
    private val peopleDataSource: PeopleDataSource,
    private val queryMap: Map<String, String>,
) : PeopleRepository {
    override fun getPersonDetails(personId: Int): Flow<Result<PersonDetails>> =
        getSingleFromResponse(
            request = { peopleDataSource.getPersonDetails(personId, queryMap) },
            mapper = { response -> response.mapToPersonDetails() }
        )

    //todo need to cache this people
    override fun getPopularPersons(): Flow<Result<List<Person>>> =
        getListFromResponse(
            request = { peopleDataSource.getPopularPersons(queryMap) },
            mapper = { response -> response.results.map { it.mapToPerson() } }
        )

    override fun getTrendingPersons(timeWindow: TimeWindow): Flow<Result<List<Person>>> =
        getListFromResponse(
            request = { peopleDataSource.getTrendingPerson(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToPerson() } }
        )

    override fun getMovieCredits(personId: Int): Flow<Result<MovieCredit>> = getSingleFromResponse(
        request = { peopleDataSource.getMovieCredits(personId, queryMap) },
        mapper = { response -> response.mapToMovieCredit() }
    )
}