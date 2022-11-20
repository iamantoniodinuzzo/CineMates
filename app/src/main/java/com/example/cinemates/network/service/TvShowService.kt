package com.example.cinemates.network.service


import com.example.cinemates.model.entities.*
import com.example.cinemates.model.entities.Collection
import com.example.cinemates.network.response.CreditsResponse
import com.example.cinemates.network.response.GenericResponse
import com.example.cinemates.network.response.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:44
 */
interface TvShowService : MediaService {

    @GET("tv/popular")
    suspend fun getPopular(@QueryMap queries: HashMap<String, String>): GenericResponse<TvShow>

    @GET("tv/upcoming")
    suspend fun getUpcoming(@QueryMap queries: HashMap<String, String>): GenericResponse<TvShow>

    @GET("tv/{tv_id}")
    suspend fun getMovieDetails(
        @Path("tv_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): TvShow

    @GET("tv/{tv_id}/credits")
    suspend fun getMovieCredits(
        @Path("tv_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): CreditsResponse

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilar(
        @Path("tv_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): GenericResponse<TvShow>

    @GET("tv/{tv_id}/videos")
    suspend fun getVideos(
        @Path("tv_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): GenericResponse<Video>


    @GET("tv/{tv_id}/images")
    suspend fun getImages(
        @Path("tv_id") id: Int,
        @QueryMap queries: HashMap<String, String>
    ): ImagesResponse


    @GET("search/tv")
    suspend fun getTvShowBySearch(@QueryMap queries: HashMap<String, String>): GenericResponse<TvShow>

    @GET("discover/tv")
    suspend fun getTvShowByDiscover(@QueryMap queries: HashMap<String, String>): GenericResponse<TvShow>

    @GET("genre/tv/list")
    suspend fun getGenreList(@QueryMap queries: HashMap<String, String>): GenericResponse<Genre>

}