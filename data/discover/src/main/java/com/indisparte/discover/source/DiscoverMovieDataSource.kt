package com.indisparte.discover.source

import com.indisparte.filter.MovieSortOptions
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
        @Query("sort_by") sortBy: String? = MovieSortOptions.DescendingPopularity.order,
        @Query("vote_average.gte") voteAverageGTE: Float? = null,
        @Query("with_cast") castIds: String? = null,
        @Query("with_genres") genreIds: String? = null,
        @Query("with_runtime.lte") runtimeLTe: Long?,
        @QueryMap map: Map<String, String>,
    ): Response<GenericResponse<MovieDTO>>
}