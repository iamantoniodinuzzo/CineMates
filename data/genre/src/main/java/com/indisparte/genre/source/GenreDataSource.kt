package com.indisparte.genre.source

import com.indisparte.response.GenreResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface GenreDataSource {
    @GET("genre/movie/list")
    suspend fun getMovieGenreList(@QueryMap queries: Map<String, String>): Response<GenreResponseDTO>

    @GET("genre/tv/list")
    suspend fun getTvGenreList(@QueryMap queries: Map<String, String>): Response<GenreResponseDTO>
}