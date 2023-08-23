package com.indisparte.discover.repository

import com.indisparte.discover.util.MediaDiscoverFilter
import com.indisparte.model.entity.Movie
import com.indisparte.network.Result
import com.indisparte.response.MovieDTO
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
interface MovieDiscoverRepository {
    suspend fun discoverMoviesByFilter(movieFilter: MediaDiscoverFilter): Flow<Result<List<Movie>>>
}