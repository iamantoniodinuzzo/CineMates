package com.indisparte.genre.repository.fake

import com.indisparte.base.MediaType
import com.indisparte.common.Genre
import com.indisparte.genre.repository.GenreRepository
import com.indisparte.network.util.Result
import com.indisparte.network.exception.CineMatesException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeGenreRepository : GenreRepository {
    private val movieGenres = mutableListOf<Genre>()
    private val tvGenres = mutableListOf<Genre>()
    private val localGenres = mutableListOf<Genre>()
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

    override fun getMovieGenreList(): Flow<Result<List<Genre>>> {
        val movieGenres =
            localGenres.filter { it.mediaType == MediaType.MOVIE || it.mediaType == MediaType.MOVIE_TV }
        return emitResult(movieGenres)
    }

    override fun getGenresByIds(genresId: List<Int>): Flow<List<Genre>> = flow {
        val genres = localGenres.filter { movieGenre ->
            movieGenre.id in genresId
        }
        emit(genres)
    }

    override fun getTvGenreList(): Flow<Result<List<Genre>>> {
        val tvGenres =
            localGenres.filter { it.mediaType == MediaType.TV || it.mediaType == MediaType.MOVIE_TV }
        return emitResult(tvGenres)
    }

    override fun getAllGenres(): Flow<List<Genre>> = flow {
        emit(localGenres)
    }

    override fun updateSavedGenre(genre: Genre): Flow<Int> = flow {
        val updated = localGenres.find { it.id == genre.id }?.let {
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
        localGenres.addAll(movieGenres)
        localGenres.addAll(tvGenres)

    }

    fun addMovieGenres(genres: List<Genre>) {
        localGenres.addAll(genres)
    }

    fun addTvGenres(genres: List<Genre>) {
        localGenres.addAll(genres)
    }

    // Helper method to clear all fake data
    fun clearData() {
        movieGenres.clear()
        tvGenres.clear()
        localGenres.clear()
    }
}
