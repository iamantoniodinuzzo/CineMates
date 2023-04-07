package com.example.cinemates.repositories

import com.example.cinemates.api.service.ActorService
import com.example.cinemates.api.service.MovieService
import com.example.cinemates.model.*
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
    private val movieService: MovieService,
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

    fun getActorCharacters(id:Int):Flow<List<Cast>> = flow{
        val castList = actorService.getMovieCredits(id, queryMap).cast
        emit(castList)
    }

    fun getActorCrewWork(id:Int):Flow<List<Crew>> = flow {
        val crew = actorService.getMovieCredits(id,queryMap).crew
        emit(crew)
    }

    fun getMoviesByActor(id: Int):Flow<List<Movie>> = flow {
        queryMap["with_cast"] = id.toString()
        val movies = movieService.getByDiscover(queryMap).results
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