package com.indisparte.actor.repository

import com.indisparte.actor.mapper.mapToPerson
import com.indisparte.actor.mapper.mapToPersonDetails
import com.indisparte.actor.source.PeopleDataSource
import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.PersonDetails
import com.indisparte.network.Resource
import com.indisparte.network.getListFromResponse
import com.indisparte.network.getSingleFromResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PeopleRepositoryImpl
@Inject
constructor(
    private val peopleDataSource: PeopleDataSource,
    private val queryMap: Map<String, String>,
) : PeopleRepository {
    override suspend fun getPersonDetails(personId: Int): Flow<Resource<PersonDetails>> =
        getSingleFromResponse(
            request = { peopleDataSource.getPersonDetails(personId, queryMap) },
            mapper = { response -> response.mapToPersonDetails() }
        )

    override suspend fun getPopularPersons(): Flow<Resource<List<Person>>> =
        getListFromResponse(
            request = { peopleDataSource.getPopularPersons(queryMap) },
            mapper = { response -> response.results.map { it.mapToPerson() } }
        )

    override suspend fun getTrendingPersons(timeWindow: TimeWindow): Flow<Resource<List<Person>>> =
        getListFromResponse(
            request = { peopleDataSource.getTrendingPerson(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToPerson() } }
        )

}