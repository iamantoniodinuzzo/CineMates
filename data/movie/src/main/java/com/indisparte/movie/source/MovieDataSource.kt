package com.indisparte.movie.source

import com.indisparte.movie.response.CastDTO
import com.indisparte.movie.response.CollectionDetailsResponseDTO
import com.indisparte.movie.response.CrewDTO
import com.indisparte.movie.response.MovieDTO
import com.indisparte.movie.response.MovieDetailsDTO
import com.indisparte.movie.response.ReleaseDatesByCountryDTO
import com.indisparte.network.GenericResponse
import com.indisparte.response.CreditResponseDTO
import com.indisparte.response.ImagesResponseDTO
import com.indisparte.response.VideoResponseDTO
import com.indisparte.response.WatchProvidersResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface MovieDataSource {

    @GET("movie/{filter}")
    suspend fun getListOfSpecificMovies(
        @Path("filter") filter: String,
        @QueryMap queryMap: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>

    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<MovieDetailsDTO>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<CreditResponseDTO<CastDTO, CrewDTO>>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<VideoResponseDTO>

    @GET("movie/{movie_id}/images")
    suspend fun getImages(
        @Path("movie_id") movie_id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<ImagesResponseDTO>

    @GET("movie/{movie_id}/watch/providers")
    suspend fun getWatchProviders(
        @Path("movie_id") movieId: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<WatchProvidersResponseDTO>

    @GET("movie/{movie_id}/release_dates")
    suspend fun getReleaseDates(
        @Path("movie_id") movieId: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<ReleaseDatesByCountryDTO>>

    @GET("search/movie")
    suspend fun getBySearch(@QueryMap queries: Map<String, String>): Response<GenericResponse<MovieDTO>>

    @GET("discover/movie")
    suspend fun getByDiscover(@QueryMap queries: Map<String, String>): Response<GenericResponse<MovieDTO>>

    @GET("trending/movie/{time_window}")
    suspend fun getTrending(
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>

    @GET("collection/{collection_id}")
    suspend fun getCollectionParts(
        @Path("collection_id") collectionId: Int,
        @QueryMap queryMap: MutableMap<String, String>,
    ):Response<CollectionDetailsResponseDTO>

}
