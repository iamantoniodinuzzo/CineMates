package com.example.cinemates.data.remote.repository

import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.data.remote.response.credits.CrewDTO
import com.example.cinemates.data.remote.response.credits.PersonDTO
import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.data.remote.response.movie.MovieDTO
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface ActorRepository {
    fun getTrendingPerson(mediaType: String, timeWindow: String): Flow<List<PersonDTO>>
    fun getActorDetails(personId: Int): Flow<PersonDTO>
    fun getActorCharacters(id: Int): Flow<List<CastDTO>>
    fun getActorCrewWork(id: Int): Flow<List<CrewDTO>>
    fun getPeoplesBySearch(query: String): Flow<List<PersonDTO>>
    fun getImages(id: Int): Flow<List<ImageDTO>>
}