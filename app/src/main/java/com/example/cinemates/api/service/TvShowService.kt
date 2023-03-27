package com.example.cinemates.api.service


import com.example.cinemates.api.response.CreditsResponse
import com.example.cinemates.api.response.GenericResponse
import com.example.cinemates.api.response.ImagesResponse
import com.example.cinemates.model.EpisodeGroup
import com.example.cinemates.model.Genre
import com.example.cinemates.model.TvShow
import com.example.cinemates.model.Video
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:44
 */
interface TvShowService {

    @GET("tv/{filter}")
    suspend fun getListOfSpecificTv(
        @Path("filter") filter: String,
        @QueryMap queryMap: Map<String, String>
    ): GenericResponse<TvShow>

    @GET("tv/{tv_id}")
    suspend fun getDetails(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): TvShow

    @GET("tv/{tv_id}/credits")
    suspend fun getCredits(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): CreditsResponse

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilar(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<TvShow>


    @GET("tv/{tv_id}/videos")
    suspend fun getVideos(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<Video>


    @GET("tv/{tv_id}/images")
    suspend fun getImages(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): ImagesResponse


    @GET("search/tv")
    suspend fun getBySearch(@QueryMap queries: Map<String, String>): GenericResponse<TvShow>

    @GET("discover/tv")
    suspend fun getByDiscover(@QueryMap queries: Map<String, String>): GenericResponse<TvShow>

    @GET("genre/tv/list")
    suspend fun getGenreList(@QueryMap queries: Map<String, String>): GenericResponse<Genre>

    @GET("trending/tv/{time_window}")
    suspend fun getTrending(
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<TvShow>

    @GET("tv/{tv_id}/episode_groups")
    suspend fun getEpisodesGroup(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<EpisodeGroup>

    @GET("tv/episode_group/{id}")
    suspend fun getEpisodeGroupDetails(
        @Path("id") id: String,
        @QueryMap queries: Map<String, String>
    ):EpisodeGroup
}