package com.example.cinemates.repository

import com.example.cinemates.api.service.ActorService
import com.example.cinemates.model.Image
import com.example.cinemates.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

     fun getTrendingPerson(mediaType: String, timeWindow: String): Flow<List<Person>> =
        flow {
            val trending = actorService.getTrendingPerson(mediaType, timeWindow, sMap).results
            emit(trending)
        }

     fun getActorDetails(personId: Int): Flow<Person> = flow {
        val actor = actorService.getActorDetails(personId, sMap)
        emit(actor)
    }

     fun getPeoplesBySearch(query: String): Flow<List<Person>> = flow {
        sMap["query"] = query
        val persons = actorService.getPeoplesBySearch(sMap).results
        emit(persons)
    }
     fun getImages(id: Int): Flow<List<Image>> = flow {
        val images = actorService.getActorImages(id, sMap).profiles
        emit(images)
    }

}