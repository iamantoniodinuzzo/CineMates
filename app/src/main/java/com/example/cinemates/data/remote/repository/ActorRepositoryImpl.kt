package com.example.cinemates.data.remote.repository

import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.data.remote.response.credits.CrewDTO
import com.example.cinemates.data.remote.response.credits.PersonDTO
import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.data.remote.response.movie.MovieDTO
import com.example.cinemates.data.remote.service.ActorService
import com.example.cinemates.data.remote.service.MovieService
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorRepositoryImpl
@Inject
constructor(
    private val actorService: ActorService,
    private val queryMap: MutableMap<String, String>
) : ActorRepository {


    override  fun getTrendingPerson(timeWindow: TimeWindow): Flow<List<PersonDTO>> =
        flow {
            val trending = actorService.getTrendingPerson(timeWindow.value, queryMap).results
            emit(trending)
        }

    override fun getActorDetails(personId: Int): Flow<PersonDTO> = flow {
        val actor = actorService.getActorDetails(personId, queryMap)
        emit(actor)
    }

    override fun getActorCharacters(id:Int):Flow<List<CastDTO>> = flow{
        val castList = actorService.getMovieCredits(id, queryMap).cast
        emit(castList)
    }

    override fun getActorCrewWork(id:Int):Flow<List<CrewDTO>> = flow {
        val crew = actorService.getMovieCredits(id,queryMap).crew
        emit(crew)
    }

    override fun getPeoplesBySearch(query: String): Flow<List<PersonDTO>> = flow {
        queryMap["query"] = query
        val persons = actorService.getPeoplesBySearch(queryMap).results
        emit(persons)
    }

    override fun getImages(id: Int): Flow<List<ImageDTO>> = flow {
        val images = actorService.getActorImages(id, queryMap).profiles
        emit(images)
    }


}