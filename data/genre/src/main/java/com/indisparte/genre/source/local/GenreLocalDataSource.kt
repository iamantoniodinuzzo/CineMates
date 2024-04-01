package com.indisparte.genre.source.local

import com.indisparte.base.MediaType
import com.indisparte.common.Genre
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.dao.UserDao
import com.indisparte.database.entity.asDomain
import com.indisparte.database.entity.asEntity
import com.indisparte.database.entity.relations.UserFavGenreCrossRef
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class GenreLocalDataSource
@Inject
constructor(
    private val genreDao: GenreDao,
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend fun insertGenres(genres: List<Genre>, mediaType: MediaType): Boolean =
        withContext(ioDispatcher) {
            val genreEntities = genres.map { it.asEntity() }
            val entities = genreEntities.map { it.copy(mediaType = mediaType.id) }
            val insertionResult = genreDao.insertAll(entities)

            return@withContext insertionResult.all { it > 0 }
        }

    suspend fun getAllSavedGenres() = withContext(ioDispatcher) {
        val entityData = genreDao.getAllGenres()
        val mappedData = entityData.map { it.asDomain() }
        return@withContext mappedData
    }

    suspend fun getAllMovieGenres() = withContext(ioDispatcher) {
        val entityData = genreDao.getAllGenresByMediaType(MediaType.MOVIE.id)
        val mappedData = entityData.map { it.asDomain() }
        return@withContext mappedData
    }

    suspend fun getAllTvGenres() = withContext(ioDispatcher) {
        val entityData = genreDao.getAllGenresByMediaType(MediaType.TV.id)
        val mappedData = entityData.map { it.asDomain() }
        return@withContext mappedData
    }

    suspend fun getAllUserFavGenres(): List<Genre> = withContext(ioDispatcher) {
        // FIXME: get user id from params or shared prefs
        val entityData = userDao.getUserFavGenres(userId = 0)
        val mappedData = entityData[0].genres.map { it.asDomain() }
        return@withContext mappedData
    }

    /*
        fun updateGenre(genre: Genre) = flow {
            val mappedGenre = genre.asEntity()
            val mappedUpdated = mappedGenre.copy(mediaType = genre.mediaType?.id)
            val result = genreDao.update(mappedUpdated)
            Timber.tag("GenreLocalDataSource").d("Update $result row with $mappedGenre")
            emit(result)
        }.flowOn(ioDispatcher)
    */

    suspend fun getAllGenresById(genresIds: List<Int>): List<Genre> = withContext(ioDispatcher) {
        val entityGenres = genreDao.getAllGenresById(genresIds)
        val mappedGenres = entityGenres.map { it.asDomain() }
        return@withContext mappedGenres
    }

    suspend fun setGenreAsFavorite(genre: Genre):Boolean = withContext(ioDispatcher) {
        val savedGenre = genreDao.getGenreById(genre.id)
        if (savedGenre == null) {
            //Never saved this genre before
            val insertionResult = genreDao.insert(genre.asEntity())
            if (insertionResult <= 0)
                return@withContext false
        }

        //Set genre as user fav
        val favDate = Date(System.currentTimeMillis())
        val crossRef = UserFavGenreCrossRef(userId = 0, genreId = genre.id, favDate = favDate)
        val insertionResult = userDao.insertUserFavGenreCrossRef(crossRef)
        return@withContext insertionResult > 0

    }

    suspend fun removeGenreAsFavorite(genreId:Int, userId:Int):Boolean = withContext(ioDispatcher){
        val crossRefToRemove = userDao.getUserFavGenre(userId, genreId) ?: //No fav genre to remove
        return@withContext false

        val deletionResult = userDao.deleteUserFavGenreCrossRef(crossRefToRemove)
        return@withContext deletionResult >= 0

    }


}