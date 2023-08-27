package com.indisparte.discover.repository

import com.indisparte.filter.MediaDiscoverFilter
import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
fun interface MovieDiscoverRepository {
    fun discoverMoviesByFilter(movieFilter: MediaDiscoverFilter): Flow<Result<List<Movie>>>
}