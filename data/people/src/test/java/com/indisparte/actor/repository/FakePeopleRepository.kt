package com.indisparte.actor.repository

import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.MovieCredit
import com.indisparte.network.Result
import com.indisparte.person.Person
import com.indisparte.person.PersonDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
/**
 * @author Antonio Di Nuzzo
 */
class FakePeopleRepository : PeopleRepository {
    private val personDetailsMap = mutableMapOf<Int, PersonDetails>()
    private val popularPersons = mutableListOf<Person>()
    private val trendingPersonsMap = mutableMapOf<TimeWindow, List<Person>>()
    private val movieCreditsMap = mutableMapOf<Int, List<MovieCredit>>()

    override fun getPersonDetails(personId: Int): Flow<Result<PersonDetails>> {
        // Implement the behavior to return fake data for getPersonDetails
        val fakeData = personDetailsMap[personId]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getPopularPersons(): Flow<Result<List<Person>>> {
        // Implement the behavior to return fake data for getPopularPersons
        return flow { emit(Result.Success(popularPersons)) }
    }

    override fun getTrendingPersons(timeWindow: TimeWindow): Flow<Result<List<Person>>> {
        // Implement the behavior to return fake data for getTrendingPersons
        val fakeData = trendingPersonsMap[timeWindow]
        return flow { emit(Result.Success(fakeData ?: emptyList())) }
    }

    override fun getMovieCredits(personId: Int): Flow<Result<List<MovieCredit>>> {
        // Implement the behavior to return fake data for getMovieCredits
        val fakeData = movieCreditsMap[personId]
        return flow { emit(Result.Success(fakeData ?: emptyList())) }
    }

    // Helper methods to set fake data in the fake repository
    fun addPersonDetails(personId: Int, details: PersonDetails) {
        personDetailsMap[personId] = details
    }

    fun addPopularPersons(personList: List<Person>) {
        popularPersons.addAll(personList)
    }

    fun addTrendingPersons(timeWindow: TimeWindow, personList: List<Person>) {
        trendingPersonsMap[timeWindow] = personList
    }

    fun addMovieCredits(personId: Int, credits: List<MovieCredit>) {
        movieCreditsMap[personId] = credits
    }

    // Helper method to clear all fake data
    fun clearData() {
        personDetailsMap.clear()
        popularPersons.clear()
        trendingPersonsMap.clear()
        movieCreditsMap.clear()
        // Clear data for other functions as well
    }
}
