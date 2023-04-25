package com.example.cinemates.data.remote.service

import com.example.cinemates.data.remote.response.credits.ActorImageResponse
import com.example.cinemates.data.remote.response.GenericResponse
import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.data.remote.response.credits.CreditsResponse
import com.example.cinemates.data.remote.response.credits.PersonDTO
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
        @QueryMap queries: Map<String, String>
    ): PersonDTO

    @GET("person/{person_id}/images")
    suspend fun getActorImages(
        @Path("person_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): ActorImageResponse

    @GET("person/{person_id}/movie_credits")
    suspend fun getMovieCredits(
        @Path("person_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): CreditsResponse

    @GET("search/person")
    suspend fun getPeoplesBySearch(@QueryMap queries: Map<String, String>): GenericResponse<PersonDTO>


    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingPerson(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<PersonDTO>

}