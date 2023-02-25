package com.example.cinemates.api.service

import com.example.cinemates.model.Cast
import com.example.cinemates.api.response.GenericResponse
import com.example.cinemates.model.Person
import com.example.cinemates.api.response.ActorImageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface ActorService {

    @GET("person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Person

    @GET("person/{person_id}/images")
    suspend fun getActorImages(
        @Path("person_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): ActorImageResponse


    @GET("search/person")
    suspend fun getPeoplesBySearch(@QueryMap queries: HashMap<String, String>): GenericResponse<Cast>


    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingPerson(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @QueryMap queries: HashMap<String, String>
    ): GenericResponse<Person>

}