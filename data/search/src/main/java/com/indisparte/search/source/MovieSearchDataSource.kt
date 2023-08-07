package com.indisparte.search.source

import com.indisparte.model.entity.Movie
import com.indisparte.network.GenericResponse
import com.indisparte.search.response.MovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
 */
interface MovieSearchDataSource {

    @GET("search/movie")
    fun searchMovieByTitle(
        @Query("query") title: String,
        @QueryMap map: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>
}