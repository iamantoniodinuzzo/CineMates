package com.example.cinemates.model.repository

import com.example.cinemates.network.service.ActorService
import com.example.cinemates.model.entities.Cast
import com.example.cinemates.model.entities.Person
import com.example.cinemates.network.response.GenericResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.util.*
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorRepository
@Inject
constructor(
    private val actorService: ActorService
) {
    private val defaultSystemLanguage: String = Locale.getDefault().language

    companion object {
        private lateinit var sMap: HashMap<String, String>
    }

    init {
        sMap = HashMap()
        sMap["language"] =
            defaultSystemLanguage
        sMap["append_to_response"] = "images"
        sMap["include_image_language"] =
            defaultSystemLanguage
        sMap["page"] = "1"
    }

    suspend fun getTrendingPerson(mediaType: String, timeWindow: String): Flow<List<Person>> =
        flow {
            val trending = actorService.getTrendingPerson(mediaType, timeWindow, sMap).results
            emit(trending)
        }

    suspend fun getActorDetails(personId: Int): Flow<Person> = flow {
        val actor = actorService.getActorDetails(personId, sMap)
        emit(actor)
    }

    suspend fun getPeoplesBySearch(query: String): Flow<List<Person>> = flow {
        sMap["query"] = query
        val persons = actorService.getPeoplesBySearch(sMap).results
        emit(persons)
    }

}