package com.example.cinemates.network.service

import com.example.cinemates.model.entities.Media
import com.example.cinemates.network.response.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface MediaService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMedia(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @QueryMap queries: HashMap<String, String>
    ): GenericResponse<Media>
}