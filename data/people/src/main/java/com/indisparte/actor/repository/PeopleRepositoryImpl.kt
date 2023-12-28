package com.indisparte.actor.repository

import com.indisparte.actor.source.local.PeopleLocalDataSource
import com.indisparte.actor.source.remote.PeopleRemoteDataSource
import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.MovieCredit
import com.indisparte.network.util.Result
import com.indisparte.network.util.whenResources
import com.indisparte.base.Person
import com.indisparte.person.PersonDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class PeopleRepositoryImpl
@Inject
constructor(
    private val peopleRemoteDataSource: PeopleRemoteDataSource,
    private val peopleLocalDataSource: PeopleLocalDataSource,
) : PeopleRepository {
    override fun getPersonDetailsAndUpdateWithLocalData(personId: Int): Flow<Result<PersonDetails>> =
        flow {
            //Get person details from API
            peopleRemoteDataSource.getPersonDetails(personId).collect { response ->
                response.whenResources(
                    onSuccess = { personDetails ->
                        //Check if is my fav person
                        val isFavoritePerson = peopleLocalDataSource.isFavoritePerson(personId)
                        //Update attribute
                        personDetails.isFavorite = isFavoritePerson

                        emit(Result.Success(personDetails))

                    }, onError = {
                        emit(response)
                    }, onLoading = {
                        emit(response)
                    }
                )
            }
        }

    override fun getPopularPersons(): Flow<Result<List<Person>>> =
        peopleRemoteDataSource.getPopularPersons()

    override fun getTrendingPersons(timeWindow: TimeWindow): Flow<Result<List<Person>>> =
        peopleRemoteDataSource.getTrendingPersons(timeWindow)

    override fun getMovieCredits(personId: Int): Flow<Result<List<MovieCredit>>> =
        peopleRemoteDataSource.getMovieCredits(personId)

    override fun setPersonAsFavorite(person: Person): Flow<Boolean> =
        flow {
            val result = peopleLocalDataSource.insertFavoritePerson(person)
            emit(result > 0)
        }

    override fun removePersonAsFavorite(person: Person): Flow<Boolean> = flow {
        val result = peopleLocalDataSource.removeFavoritePerson(person)
        emit(result > 0)
    }

    override fun getAllFavoritePerson(): Flow<List<Person>> = flow {
        val result = peopleLocalDataSource.getAllFavoritePerson()
        emit(result)
    }
}