package com.indisparte.watch_provider.repository

import com.indisparte.model.entity.CountryResult
import com.indisparte.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface WatchProviderRepository {
    suspend fun getMovieWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Resource<List<CountryResult>>>

    suspend fun getTvWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Resource<List<CountryResult>>>
}