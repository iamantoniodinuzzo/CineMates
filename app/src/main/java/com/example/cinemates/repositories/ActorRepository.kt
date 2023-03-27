package com.example.cinemates.repositories

import com.example.cinemates.api.service.ActorService
import com.example.cinemates.model.Image
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorRepository
@Inject
constructor(
    private val actorService: ActorService,
    private val queryMap: MutableMap<String, String>
) {


    fun getTrendingPerson(mediaType: String, timeWindow: String): Flow<List<Person>> =
        flow {
            val trending = actorService.getTrendingPerson(mediaType, timeWindow, queryMap).results
            emit(trending)
        }

    fun getActorDetails(personId: Int): Flow<Person> = flow {
        val actor = actorService.getActorDetails(personId, queryMap)
        emit(actor)
    }

    fun getMovieCredits(id:Int):Flow<List<Movie>> = flow{
        val movies = actorService.getMovieCredits(id, queryMap).results
        emit(movies)
    }

    fun getPeoplesBySearch(query: String): Flow<List<Person>> = flow {
        queryMap["query"] = query
        val persons = actorService.getPeoplesBySearch(queryMap).results
        emit(persons)
    }

    fun getImages(id: Int): Flow<List<Image>> = flow {
        val images = actorService.getActorImages(id, queryMap).profiles
        emit(images)
    }


}