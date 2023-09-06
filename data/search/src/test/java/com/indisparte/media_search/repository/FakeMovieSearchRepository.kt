package com.indisparte.media_search.repository

import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeMovieSearchRepository : MovieSearchRepository {
    private val movieSearchResults = mutableMapOf<String, List<Movie>>()
    private var cineMatesExceptions: CineMatesExceptions? = null
    private var shouldEmitException: Boolean = false

    override fun searchMovieByTitle(title: String): Flow<Result<List<Movie>>> {
        // Implement the behavior to return fake data for searchMovieByTitle
        val fakeData = movieSearchResults[title] ?: emptyList()
        return emitResult(fakeData)
    }

    // Helper method to set fake data in the fake repository
    fun addSearchResults(title: String, results: List<Movie>) {
        movieSearchResults[title] = results
    }

    // Helper method to clear all fake data
    fun clearData() {
        movieSearchResults.clear()
    }

    fun setShouldEmitException(emit: Boolean) {
        shouldEmitException = emit
    }

    fun setExceptionToEmit(cineMatesExceptions: CineMatesExceptions) {
        this.cineMatesExceptions = cineMatesExceptions
    }
    private fun <T> emitResult(data: T): Flow<Result<T>> {
        return if (shouldEmitException) {
            flow {
                emit(Result.Error(cineMatesExceptions ?: CineMatesExceptions.GenericException))
            }
        } else {
            flow {
                emit(Result.Success(data))
            }
        }
    }
}
