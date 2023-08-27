package com.indisparte.discover.repository

import com.indisparte.discover.util.MediaDiscoverFilter
import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
interface MovieDiscoverRepository {
    fun discoverMoviesByFilter(movieFilter: MediaDiscoverFilter): Flow<Result<List<Movie>>>
}