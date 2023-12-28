package com.indisparte.movie_data.source.remote

import com.indisparte.movie_data.response.CastDTO
import com.indisparte.movie_data.response.CollectionDetailsResponseDTO
import com.indisparte.movie_data.response.CrewDTO
import com.indisparte.response.MovieDTO
import com.indisparte.movie_data.response.MovieDetailsDTO
import com.indisparte.movie_data.response.ReleaseDatesByCountryDTO
import com.indisparte.network.response.GenericResponse
import com.indisparte.response.CreditResponseDTO
import com.indisparte.response.ImagesResponseDTO
import com.indisparte.response.VideoResponseDTO
import com.indisparte.response.WatchProvidersResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
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
        @Path("movie_id") movieId: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movieId: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<VideoResponseDTO>

    @GET("movie/{movie_id}/images")
    suspend fun getImages(
        @Path("movie_id") movieId: Int,
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

    @GET("trending/movie/{time_window}")
    suspend fun getTrending(
        @Path("time_window") timeWindow: String,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>

    @GET("collection/{collection_id}")
    suspend fun getCollectionDetails(
        @Path("collection_id") collectionId: Int,
        @QueryMap queryMap: Map<String, String>,
    ):Response<CollectionDetailsResponseDTO>

}
