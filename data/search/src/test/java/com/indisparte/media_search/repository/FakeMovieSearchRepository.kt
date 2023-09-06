package com.indisparte.media_search.repository

import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeMovieSearchRepository : MovieSearchRepository {
    private val movieSearchResults = mutableMapOf<String, List<Movie>>()

    override fun searchMovieByTitle(title: String): Flow<Result<List<Movie>>> {
        // Implement the behavior to return fake data for searchMovieByTitle
        val fakeData = movieSearchResults[title] ?: emptyList()
        return flow { emit(Result.Success(fakeData)) }
    }

    // Helper method to set fake data in the fake repository
    fun addSearchResults(title: String, results: List<Movie>) {
        movieSearchResults[title] = results
    }

    // Helper method to clear all fake data
    fun clearData() {
        movieSearchResults.clear()
    }
}
