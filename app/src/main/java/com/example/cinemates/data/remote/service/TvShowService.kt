package com.example.cinemates.data.remote.service


import com.example.cinemates.data.remote.response.GenericResponse
import com.example.cinemates.data.remote.response.genre.GenreDTO
import com.example.cinemates.data.remote.response.credits.CreditsResponse
import com.example.cinemates.data.remote.response.genre.GenresResponse
import com.example.cinemates.data.remote.response.image.ImagesResponse
import com.example.cinemates.data.remote.response.trailer.VideoDTO
import com.example.cinemates.data.remote.response.tvShow.*
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
    ): GenericResponse<TvShowDTO>

    @GET("tv/{tv_id}")
    suspend fun getDetails(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): TvShowDetailsDTO

    @GET("tv/{tv_id}/credits")
    suspend fun getCredits(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): CreditsResponse

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilar(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<TvShowDTO>


    @GET("tv/{tv_id}/videos")
    suspend fun getVideos(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<VideoDTO>


    @GET("tv/{tv_id}/images")
    suspend fun getImages(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): ImagesResponse


    @GET("search/tv")
    suspend fun getBySearch(@QueryMap queries: Map<String, String>): GenericResponse<TvShowDTO>

    @GET("discover/tv")
    suspend fun getByDiscover(@QueryMap queries: Map<String, String>): GenericResponse<TvShowDTO>

    @GET("genre/tv/list")
    suspend fun getGenreList(@QueryMap queries: Map<String, String>): GenresResponse

    @GET("trending/tv/{time_window}")
    suspend fun getTrending(
        @Path("time_window") time_window: String,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<TvShowDTO>

    @GET("tv/{tv_id}/episode_groups")
    suspend fun getEpisodesGroup(
        @Path("tv_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): GenericResponse<EpisodeGroupDTO>

    @GET("tv/episode_group/{id}")
    suspend fun getEpisodeGroupDetails(
        @Path("id") id: String,
        @QueryMap queries: Map<String, String>
    ): EpisodeGroupDetailsDTO

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getSeasonDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @QueryMap queries: Map<String, String>
    ): SeasonDetailsDTO
}