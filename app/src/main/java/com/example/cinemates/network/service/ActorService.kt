package com.example.cinemates.network.service

import com.example.cinemates.model.entities.Cast
import com.example.cinemates.network.response.GenericResponse
import com.example.cinemates.network.response.ImagesResponse
import com.example.cinemates.model.entities.Person
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface ActorService {

    @GET("person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<Person>

    @GET("person/{person_id}/images")
    suspend fun getActorImages(
        @Path("person_id") id: Int,
        @Query("api_key") api: String
    ): Response<ImagesResponse>


    @GET("search/person")
    suspend fun getPeoplesBySearch(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Cast>>


    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingPerson(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @QueryMap queries: HashMap<String, String>
    ): Response<GenericResponse<Person>>

}