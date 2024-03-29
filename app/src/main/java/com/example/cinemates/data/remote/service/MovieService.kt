package com.example.cinemates.data.remote.service


import com.example.cinemates.data.remote.response.GenericResponse
import com.example.cinemates.data.remote.response.genre.GenreDTO
import com.example.cinemates.data.remote.response.credits.CreditsResponse
import com.example.cinemates.data.remote.response.genre.GenresResponse
import com.example.cinemates.data.remote.response.image.ImagesResponse
import com.example.cinemates.data.remote.response.movie.CollectionDTO
import com.example.cinemates.data.remote.response.movie.MovieDTO
import com.example.cinemates.data.remote.response.movie.MovieDetailsDTO
import com.example.cinemates.data.remote.response.trailer.VideoDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:44
 */
interface MovieService {

    /**
     * This method generify the getPopular, getUpcoming , getTopRated and now playing
     */
    @GET("movie/{filter}")
    suspend fun getListOfSpecificMovies(
        @Path("filter") filter: String,
        @QueryMap queryMap: Map<String, String>
    ): GenericResponse<MovieDTO>

    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): MovieDetailsDTO

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): CreditsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<MovieDTO>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommended(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<MovieDTO>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<VideoDTO>


    @GET("movie/{movie_id}/images")
    suspend fun getImages(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>
    ): ImagesResponse

    @GET("collection/{collection_id}")
    suspend fun getCollection(
        @Path("collection_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): CollectionDTO

    @GET("search/movie")
    suspend fun getBySearch(@QueryMap queries: Map<String, String>): GenericResponse<MovieDTO>

    @GET("discover/movie")
    suspend fun getByDiscover(@QueryMap queries: Map<String, String>): GenericResponse<MovieDTO>

    @GET("genre/movie/list")
    suspend fun getGenreList(@QueryMap queries: Map<String, String>): GenresResponse

    @GET("trending/movie/{time_window}")
    suspend fun getTrending(
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<MovieDTO>

}