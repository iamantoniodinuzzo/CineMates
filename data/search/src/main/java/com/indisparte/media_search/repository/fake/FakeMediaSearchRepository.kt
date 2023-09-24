package com.indisparte.media_search.repository.fake

import com.indisparte.media_search.repository.MediaSearchRepository
import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import com.indisparte.tv.TvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeMediaSearchRepository : MediaSearchRepository {
    private val movieSearchResults = mutableMapOf<String, List<Movie>>()
    private val tvShowSearchResults = mutableMapOf<String, List<TvShow>>()
    private var cineMatesExceptions: CineMatesExceptions? = null
    private var shouldEmitException: Boolean = false

    override fun searchMovieByTitle(title: String): Flow<Result<List<Movie>>> {
        val fakeData = movieSearchResults[title]
        return emitResult(fakeData)
    }

    override fun searchTvByTitle(title: String): Flow<Result<List<TvShow>>> {
        val fakeData = tvShowSearchResults[title]
        return emitResult(fakeData)
    }

    fun addSearchResults(title: String, results: List<Movie>) {
        movieSearchResults[title] = results
    }

    fun clearData() {
        movieSearchResults.clear()
    }

    fun setShouldEmitException(emit: Boolean) {
        shouldEmitException = emit
    }

    fun setExceptionToEmit(cineMatesExceptions: CineMatesExceptions) {
        this.cineMatesExceptions = cineMatesExceptions
    }

    private fun <T> emitResult(data: T?): Flow<Result<T>> {
        return if (shouldEmitException || data == null) {
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
