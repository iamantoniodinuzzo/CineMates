package com.indisparte.genre.repository.fake

import com.indisparte.common.Genre
import com.indisparte.genre.repository.GenreRepository
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
    private val localGenres = mutableListOf<Genre>()
    private var cineMatesExceptions: CineMatesExceptions? = null
    private var shouldEmitException: Boolean = false

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

    override fun getMovieGenreList(): Flow<Result<List<Genre>>> {
        return emitResult(movieGenres)
    }

    override fun getGenresByIds(genresId: List<Int>): Flow<List<Genre>> = flow {
        val genres = localGenres.filter { movieGenre ->
            movieGenre.id in genresId
        }
        emit(genres)
    }

    override fun getTvGenreList(): Flow<Result<List<Genre>>> {
        return emitResult(tvGenres)
    }

    override fun getAllGenres(): Flow<List<Genre>> = flow {
        emit(localGenres)
    }

    override fun updateSavedGenre(genre: Genre): Flow<Int> = flow {
        val updated = movieGenres.find { it.id == genre.id }?.let {
            it.isFavorite = genre.isFavorite
            1
        } ?: 0
        emit(updated)
    }

    override fun getMyFavGenres(): Flow<List<Genre>> = flow {
        val myFavGenres = localGenres.filter { it.isFavorite }
        emit(myFavGenres)
    }

    override suspend fun fetchAllGenres() {
        val allGenres = movieGenres.union(tvGenres).toList()
        localGenres.addAll(allGenres)
    }

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
        localGenres.clear()
    }
}
