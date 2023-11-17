package com.indisparte.movie_data.source.local

import com.indisparte.base.Media
import com.indisparte.base.MediaType
import com.indisparte.database.dao.MediaDao
import com.indisparte.database.entity.asDomain
import com.indisparte.database.entity.asEntity
import com.indisparte.movie_data.Movie
import kotlinx.coroutines.CoroutineDispatcher
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
    private val mediaDao: MediaDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun insertFavoriteMovie(movie: Movie): Boolean = withContext(ioDispatcher) {
        val entity = movie.asEntity()
        val deferredInsertionResult = async {
            mediaDao.insertFavoriteMedia(entity)
        }

        return@withContext deferredInsertionResult.await()
    }

    suspend fun insertToSeeMovie(movie: Movie): Boolean = withContext(ioDispatcher) {
        val entity = movie.asEntity()
        val deferredInsertionResult = async {
            mediaDao.insertToSeeMedia(entity)
        }

        return@withContext deferredInsertionResult.await()
    }

    suspend fun insertSeenMovie(movie: Movie): Boolean = withContext(ioDispatcher) {
        val entity = movie.asEntity()
        val deferredInsertionResult = async {
            mediaDao.insertSeenMedia(entity)
        }

        return@withContext deferredInsertionResult.await()
    }

    suspend fun isFavoriteMovie(movieId: Int): Boolean = withContext(ioDispatcher) {
        val deferredBoolean = async { mediaDao.isMediaFavorite(movieId) }

        return@withContext deferredBoolean.await()
    }

    suspend fun isToSeeMovie(movieId: Int): Boolean = withContext(ioDispatcher) {
        val deferredBoolean = async { mediaDao.isMediaToSee(movieId) }

        return@withContext deferredBoolean.await()
    }

    suspend fun isSeenMovie(movieId: Int): Boolean = withContext(ioDispatcher) {
        val deferredBoolean = async { mediaDao.isMediaSeen(movieId) }

        return@withContext deferredBoolean.await()
    }

    suspend fun getAllFavoriteMedia(): List<Media> = withContext(ioDispatcher) {
        val deferredList = async { mediaDao.getAllFavoriteMediaByMediaType(MediaType.MOVIE.id) }

        return@withContext deferredList.await().map { it.asDomain() }

    }
    suspend fun getAllToSeeMovie(): List<Media> = withContext(ioDispatcher) {
        val deferredList = async { mediaDao.getToSeeMediaByMediaType(MediaType.MOVIE.id) }

        return@withContext deferredList.await().map { it.asDomain() }

    }

    suspend fun getAllSeenMovie(): List<Media> = withContext(ioDispatcher) {
        val deferredList = async { mediaDao.getSeenMediaByMediaType(MediaType.MOVIE.id) }

        return@withContext deferredList.await().map { it.asDomain() }

    }

    suspend fun removeMovieFromFavorite(movie: Movie): Boolean =
        withContext(ioDispatcher) {
            val entity = movie.asEntity()
            val deferredInt = async {
                mediaDao.deleteMediaFromFavorite(
                    entity.id
                )
            }

            val result = deferredInt.await()

            return@withContext result
        }

    suspend fun removeMovieFromSeen(movie: Movie): Boolean =
        withContext(ioDispatcher) {
            val entity = movie.asEntity()
            val deferredInt = async {
                mediaDao.deleteMediaFromSeen(
                    entity.id
                )
            }

            val result = deferredInt.await()

            return@withContext result
        }

    suspend fun removeMovieFromToSee(movie: Movie): Boolean =
        withContext(ioDispatcher) {
            val entity = movie.asEntity()
            val deferredInt = async {
                mediaDao.deleteMediaFromToSee(
                    entity.id
                )
            }

            val result = deferredInt.await()

            return@withContext result
        }



}