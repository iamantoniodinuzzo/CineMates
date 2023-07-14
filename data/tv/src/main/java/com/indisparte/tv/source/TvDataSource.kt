package com.indisparte.tv.source

import com.indisparte.network.GenericResponse
import com.indisparte.response.CreditResponseDTO
import com.indisparte.response.ImagesResponseDTO
import com.indisparte.response.VideoResponseDTO
import com.indisparte.response.WatchProvidersResponseDTO
import com.indisparte.tv.response.CastDTO
import com.indisparte.tv.response.CrewDTO
import com.indisparte.tv.response.EpisodeGroupDTO
import com.indisparte.tv.response.EpisodeGroupDetailsDTO
import com.indisparte.tv.response.SeasonDetailsDTO
import com.indisparte.tv.response.TvShowDTO
import com.indisparte.tv.response.TvShowDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface TvDataSource {

    @GET("tv/{filter}")
    suspend fun getTvListByType(
        @Path("filter") filter: String,
        @QueryMap queryMap: Map<String, String>,
    ): Response<GenericResponse<TvShowDTO>>

    @GET("tv/{tv_id}")
    suspend fun getDetails(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<TvShowDetailsDTO>

    @GET("tv/{tv_id}/credits")
    suspend fun getCredits(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<CreditResponseDTO<CastDTO, CrewDTO>>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilar(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<TvShowDTO>>


    @GET("tv/{tv_id}/videos")
    suspend fun getVideos(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<VideoResponseDTO>


    @GET("tv/{tv_id}/images")
    suspend fun getImages(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<ImagesResponseDTO>


    @GET("tv/{series_id}/episode_groups")
    suspend fun getEpisodesGroup(
        @Path("series_id") id: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<EpisodeGroupDTO>>

    @GET("tv/episode_group/{tv_episode_group_id}")
    suspend fun getEpisodeGroupDetails(
        @Path("tv_episode_group_id") id: String,
        @QueryMap queries: Map<String, String>,
    ): Response<EpisodeGroupDetailsDTO>

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getSeasonDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<SeasonDetailsDTO>

    @GET("search/tv")
    suspend fun getBySearch(@QueryMap queries: Map<String, String>): Response<GenericResponse<TvShowDTO>>

    @GET("discover/tv")
    suspend fun getByDiscover(@QueryMap queries: Map<String, String>): Response<GenericResponse<TvShowDTO>>

    @GET("trending/tv/{time_window}")
    suspend fun getTrending(
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>,
    ): Response<GenericResponse<TvShowDTO>>

    @GET("tv/{series_id}/watch/providers")
    suspend fun getWatchProviders(
        @Path("series_id") tvId: Int,
        @QueryMap queries: Map<String, String>,
    ): Response<WatchProvidersResponseDTO>

}
