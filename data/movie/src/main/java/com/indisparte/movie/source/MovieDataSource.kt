package com.indisparte.movie.source

import android.graphics.Movie
import com.indisparte.movie.util.GenericResponse
import com.indisparte.movie.response.MovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface MovieDataSource {


    /**
     * This method generify the getPopular, getUpcoming , getTopRated and now playing
     */
    @GET("movie/{filter}")
    suspend fun getListOfSpecificMovies(
        @Path("filter") filter: String,
        @QueryMap queryMap: Map<String, String>
    ): Response<GenericResponse<MovieDTO>>

    @GET("search/movie")
    suspend fun getBySearch(@QueryMap queries: Map<String, String>): Response<GenericResponse<MovieDTO>>

    @GET("discover/movie")
    suspend fun getByDiscover(@QueryMap queries: Map<String, String>): Response<GenericResponse<MovieDTO>>

    @GET("trending/movie/{time_window}")
    suspend fun getTrending(
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>
    ): Response<GenericResponse<MovieDTO>>

}