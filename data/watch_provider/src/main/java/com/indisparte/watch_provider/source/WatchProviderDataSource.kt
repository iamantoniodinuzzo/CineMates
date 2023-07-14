package com.indisparte.watch_provider.source

import com.indisparte.watch_provider.response.WatchProvidersResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface WatchProviderDataSource {
    @GET("{media_type}/{media_id}/watch/providers")
    suspend fun getWatchProviders(
        @Path("media_id") mediaId: Int,
        @Path("media_type") mediaType: String,
        @QueryMap queries: Map<String, String>
    ): Response<WatchProvidersResponseDTO>
}