package com.indisparte.media_search.source

import com.indisparte.network.GenericResponse
import com.indisparte.response.MovieDTO
import com.indisparte.response.TvShowDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
 */
interface MediaSearchDataSource {

    @GET("search/movie")
    suspend fun searchMovieByTitle(
        @Query("query") title: String,
        @QueryMap map: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>

    @GET("search/tv")
    suspend fun searchTvByTitle(
        @Query("query") title: String,
        @QueryMap map: Map<String, String>,
    ): Response<GenericResponse<TvShowDTO>>
}