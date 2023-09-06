package com.indisparte.genre.repository

import com.indisparte.common.Genre
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeGenreRepository : GenreRepository {
    private val movieGenres = mutableListOf<Genre>()
    private val tvGenres = mutableListOf<Genre>()
    private var cineMatesExceptions: CineMatesExceptions? = null
    private var shouldEmitException: Boolean = false

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
    override fun getMovieGenreList(): Flow<Result<List<Genre>>> {
        // Implement the behavior to return fake data for getMovieGenreList
        return emitResult(movieGenres)
    }

    override fun getTvGenreList(): Flow<Result<List<Genre>>> {
        // Implement the behavior to return fake data for getTvGenreList
        return emitResult(tvGenres)
    }

    // Helper methods to set fake data in the fake repository
    fun addMovieGenres(genres: List<Genre>) {
        movieGenres.addAll(genres)
    }

    fun addTvGenres(genres: List<Genre>) {
        tvGenres.addAll(genres)
    }

    // Helper method to clear all fake data
    fun clearData() {
        movieGenres.clear()
        tvGenres.clear()
    }
}
