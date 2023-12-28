package com.indisparte.discover.repository.fake

import com.indisparte.discover.repository.MovieDiscoverRepository
import com.indisparte.filter.MediaDiscoverFilter
import com.indisparte.movie_data.Movie
import com.indisparte.network.util.Result
import com.indisparte.network.exception.CineMatesException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeMovieDiscoverRepository : MovieDiscoverRepository {
    private val movieResultsMap = mutableMapOf<MediaDiscoverFilter, List<Movie>>()
    private var cineMatesExceptions: CineMatesException? = null
    private var shouldEmitException: Boolean = false

    fun setShouldEmitException(emit: Boolean) {
        shouldEmitException = emit
    }

    fun setExceptionToEmit(cineMatesExceptions: CineMatesException) {
        this.cineMatesExceptions = cineMatesExceptions
    }

    private fun <T> emitResult(data: T?): Flow<Result<T>> {
        return if (shouldEmitException || data == null) {
            flow {
                emit(Result.Error(cineMatesExceptions ?: CineMatesException.GenericException))
            }
        } else {
            flow {
                emit(Result.Success(data))
            }
        }
    }
    override fun discoverMoviesByFilter(movieFilter: MediaDiscoverFilter): Flow<Result<List<Movie>>> {
        // Implement the behavior to return fake data for discoverMoviesByFilter
        val fakeData = movieResultsMap[movieFilter]
        return emitResult(fakeData)
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
