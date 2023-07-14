package com.indisparte.actor.repository

import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.PersonDetails
import com.indisparte.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface PeopleRepository {
    suspend fun getPersonDetails(personId: Int): Flow<Resource<PersonDetails>>
    suspend fun getPopularPersons(): Flow<Resource<List<Person>>>
    suspend fun getTrendingPersons(timeWindow: TimeWindow): Flow<Resource<List<Person>>>
}