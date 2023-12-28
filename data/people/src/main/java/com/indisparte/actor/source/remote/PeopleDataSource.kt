package com.indisparte.actor.source.remote

import com.indisparte.actor.response.MovieCreditResponseDTO
import com.indisparte.actor.response.PersonDTO
import com.indisparte.actor.response.PersonDetailsDTO
import com.indisparte.network.response.GenericResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
 */
interface PeopleDataSource {

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") id: Int,
        @QueryMap queries: Map<String, String>,
        @Query("append_to_response") appendToResponse: String? = "images"
    ): Response<PersonDetailsDTO>

    @GET("person/popular")
    suspend fun getPopularPersons(
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<PersonDTO>>


    @GET("trending/person/{time_window}")
    suspend fun getTrendingPerson(
        @Path("time_window") timeWindow: String,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<PersonDTO>>

    @GET("person/{person_id}/movie_credits")
    suspend fun getMovieCredits(
        @Path("person_id") id:Int,
        @QueryMap queries: Map<String, String>,
    ):Response<MovieCreditResponseDTO>
}