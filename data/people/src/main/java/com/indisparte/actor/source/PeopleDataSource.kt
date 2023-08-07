package com.indisparte.actor.source

import com.indisparte.actor.response.PersonDTO
import com.indisparte.actor.response.PersonDetailsDTO
import com.indisparte.network.GenericResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface PeopleDataSource {

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<PersonDetailsDTO>

    @GET("person/popular")
    suspend fun getPopularPersons(
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<PersonDTO>>


    @GET("trending/person/{time_window}")
    suspend fun getTrendingPerson(
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<PersonDTO>>
}