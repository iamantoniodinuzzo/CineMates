package com.example.cinemates.api.service


import com.example.cinemates.model.Collection
import com.example.cinemates.api.response.CreditsResponse
import com.example.cinemates.api.response.GenericResponse
import com.example.cinemates.model.Genre
import com.example.cinemates.api.response.ImagesResponse
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Video
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:44
 */
interface MovieService {

    /* @GET("movie/popular")
     suspend fun getPopular(@QueryMap queries: HashMap<String, String>): GenericResponse<Movie>

     @GET("movie/upcoming")
     suspend fun getUpcoming(@QueryMap queries: HashMap<String, String>): GenericResponse<Movie>

     @GET("movie/top_rated")
     suspend fun getTopRated(@QueryMap queries: HashMap<String, String>): GenericResponse<Movie>*/

    /**
     * This method generify the getPopular, getUpcoming & getTopRated
     */
    @GET("movie/{filter}")
    suspend fun getListOfSpecificMovies(
        @Path("filter") filter: String,
        @QueryMap queryMap: Map<String, String>
    ): GenericResponse<Movie>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): CreditsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<Movie>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommended(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<Movie>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<Video>


    @GET("movie/{movie_id}/images")
    suspend fun getImages(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>
    ): ImagesResponse

    @GET("collection/{collection_id}")
    suspend fun getCollection(
        @Path("collection_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): Collection

    @GET("search/movie")
    suspend fun getMoviesBySearch(@QueryMap queries: Map<String, String>): GenericResponse<Movie>

    @GET("discover/movie")
    suspend fun getMoviesByDiscover(@QueryMap queries: Map<String, String>): GenericResponse<Movie>

    @GET("genre/movie/list")
    suspend fun getGenreList(@QueryMap queries: Map<String, String>): GenericResponse<Genre>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMovies(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<Movie>

}