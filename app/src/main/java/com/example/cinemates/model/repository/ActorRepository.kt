package com.example.cinemates.model.repository

import com.example.cinemates.network.service.ActorService
import com.example.cinemates.model.entities.Cast
import com.example.cinemates.network.response.GenericResponse
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

    suspend fun getTrendingPerson(mediaType: String, timeWindow: String) =
        actorService.getTrendingPerson(mediaType, timeWindow, sMap)

    suspend fun getActorDetails(personId: Int) = actorService.getActorDetails(
        personId,
        sMap
    )

    suspend fun getPeoplesBySearch(query: String): Response<GenericResponse<Cast>> {
        sMap["query"] = query
        return actorService.getPeoplesBySearch(sMap)
    }

}