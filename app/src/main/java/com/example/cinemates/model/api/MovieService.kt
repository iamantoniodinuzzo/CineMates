package com.example.cinemates.model.api

import com.example.cinemates.model.data.*
import com.example.cinemates.model.data.Collection
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.Response
import java.util.HashMap

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:44
 */
interface MovieService {
    @GET("movie/now_playing")
    suspend fun getCurrentlyShowing(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Movie>>

    @GET("movie/popular")
    suspend fun getPopular(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Movie>>

    @GET("movie/upcoming")
    suspend fun getUpcoming(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Movie>>

    @GET("movie/top_rated")
    suspend fun getTopRated(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Movie>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<Movie>

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<GenericResponse<Cast>>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<GenericResponse<Movie>>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<GenericResponse<Video>>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<GenericResponse<Review>>

    @GET("movie/{movie_id}/images")
    suspend fun getImages(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<Images>

    @GET("person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<Actor>

    @GET("person/{person_id}/images")
    suspend fun getActorImages(
        @Path("person_id") id: Int,
        @Query("api_key") api: String
    ): Response<Images>

    @GET("collection/{collection_id}")
    suspend fun getCollection(
        @Path("collection_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): Response<Collection>

    @GET("search/movie")
    suspend fun getMoviesBySearch(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Movie>>

    @GET("discover/movie")
    suspend fun getMoviesByDiscover(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Movie>>

    @GET("genre/movie/list")
    suspend fun getGenreList(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Genre>>

    @GET("search/person")
    suspend fun getPeoplesBySearch(@QueryMap queries: HashMap<String, String>): Response<GenericResponse<Cast>>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMovies(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @QueryMap queries: HashMap<String, String>
    ): Response<GenericResponse<Movie>>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingPerson(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @QueryMap queries: HashMap<String, String>
    ): Response<GenericResponse<Person>>
}