package com.indisparte.actor.source.remote

import com.indisparte.actor.mapper.mapToMovieCredits
import com.indisparte.actor.mapper.mapToPerson
import com.indisparte.actor.mapper.mapToPersonDetails
import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.MovieCredit
import com.indisparte.network.util.Result
import com.indisparte.network.response.getListFromResponse
import com.indisparte.network.response.getSingleFromResponse
import com.indisparte.person.Person
import com.indisparte.person.PersonDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class PeopleRemoteDataSource
@Inject
constructor(
    private val peopleDataSource: PeopleDataSource,
    private val queryMap: Map<String, String>,
) {

    fun getPersonDetails(personId: Int): Flow<Result<PersonDetails>> =
        getSingleFromResponse(
            request = { peopleDataSource.getPersonDetails(personId, queryMap) },
            mapper = { response -> response.mapToPersonDetails() }
        )

    fun getPopularPersons(): Flow<Result<List<Person>>> =
        getListFromResponse(
            request = { peopleDataSource.getPopularPersons(queryMap) },
            mapper = { response -> response.results.map { it.mapToPerson() } }
        )

    fun getTrendingPersons(timeWindow: TimeWindow): Flow<Result<List<Person>>> =
        getListFromResponse(
            request = { peopleDataSource.getTrendingPerson(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToPerson() } }
        )

    fun getMovieCredits(personId: Int): Flow<Result<List<MovieCredit>>> =
        getSingleFromResponse(
            request = { peopleDataSource.getMovieCredits(personId, queryMap) },
            mapper = { response -> response.mapToMovieCredits() }
        )
}