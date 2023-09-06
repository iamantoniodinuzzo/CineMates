package com.indisparte.discover.repository

import com.indisparte.filter.MediaDiscoverFilter
import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeMovieDiscoverRepository : MovieDiscoverRepository {
    private val movieResultsMap = mutableMapOf<MediaDiscoverFilter, List<Movie>>()

    override fun discoverMoviesByFilter(movieFilter: MediaDiscoverFilter): Flow<Result<List<Movie>>> {
        // Implement the behavior to return fake data for discoverMoviesByFilter
        val fakeData = movieResultsMap[movieFilter]
        return flow { emit(Result.Success(fakeData ?: emptyList())) }
    }

    // Helper method to set fake data in the fake repository
    fun addMovieResults(filter: MediaDiscoverFilter, results: List<Movie>) {
        movieResultsMap[filter] = results
    }

    // Helper method to clear all fake data
    fun clearData() {
        movieResultsMap.clear()
    }
}
