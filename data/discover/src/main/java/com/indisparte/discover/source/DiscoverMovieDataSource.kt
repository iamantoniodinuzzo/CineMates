package com.indisparte.discover.source

import com.indisparte.discover.util.SortOptions
import com.indisparte.model.entity.Movie
import com.indisparte.network.GenericResponse
import com.indisparte.response.MovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author Antonio Di Nuzzo
 */
interface DiscoverMovieDataSource {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("sort_by") sortBy: String = SortOptions.POPULARITY_DESC.sortType,
        @Query("vote_average.gte") voteAverageGTE: Float? = null,
        @Query("with_cast") castIds: String? = null,
        @Query("with_genres") genreIds: String? = null,
        @Query("with_runtime.lte") runtimeLTe: Long?,
        @QueryMap map: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>
}