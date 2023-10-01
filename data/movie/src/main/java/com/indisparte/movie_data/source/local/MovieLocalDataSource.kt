package com.indisparte.movie_data.source.local

import com.indisparte.base.Media
import com.indisparte.database.dao.MediaDao
import com.indisparte.database.model.asDomain
import com.indisparte.database.model.asEntity
import com.indisparte.movie_data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class MovieLocalDataSource
@Inject
constructor(
    private val dao: MediaDao,
) {

    suspend fun insertFavoriteMovie(movie: Movie): Boolean = withContext(Dispatchers.IO) {
        val entity = movie.asEntity()
        val deferredInsertionResult = async {
            dao.insertFavoriteMovie(entity)
        }

        return@withContext deferredInsertionResult.await()
    }

    suspend fun getFavoriteMovie(movieId: Int): Boolean = withContext(Dispatchers.IO) {
        val deferredBoolean = async { dao.isMediaFavorite(movieId) }

        return@withContext deferredBoolean.await()
    }

    suspend fun getAllFavoriteMedia(): List<Media> = withContext(Dispatchers.IO) {
        val deferredList = async { dao.getAllFavoriteMedia() }

        return@withContext deferredList.await().map { it.asDomain() }

    }

    suspend fun removeMovieFromFavorite(movie: Movie) {
        withContext(Dispatchers.IO) {
            val entity = movie.asEntity()
            dao.deleteMedia(
                entity
            )
        }
    }

}