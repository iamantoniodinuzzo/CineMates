package com.indisparte.movie_data.source.local

import com.indisparte.base.Media
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.dao.MediaDao
import com.indisparte.database.dao.UserDao
import com.indisparte.database.entity.asDomain
import com.indisparte.database.entity.asEntity
import com.indisparte.database.entity.relations.GenreMediaCrossRef
import com.indisparte.database.entity.relations.MediaListCrossRef
import com.indisparte.database.entity.relations.UserFavMediaCrossRef
import com.indisparte.movie_data.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class MovieLocalDataSource
@Inject
constructor(
    private val mediaDao: MediaDao,
    private val genreDao: GenreDao,
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    private fun saveMovie(movie: Movie): Boolean {
        //Save movie in media table
        val savingResult = mediaDao.insert(movie.asEntity())
        if (savingResult <= 0)
        //Unsuccessful insertion
            return false

        //Save movie genres
        val movieGenresId = movie.genreIds
        val genreMediaCrossRefList = movieGenresId.map { GenreMediaCrossRef(movie.id, it) }

        val insertionResult = genreDao.insertGenresInMedia(genreMediaCrossRefList)
        if (insertionResult.any { it <= 0 })
            return false

        return true


    }

    suspend fun insertFavoriteMovie(movie: Movie): Boolean = withContext(ioDispatcher) {
        //Check if movie already saved
        val savedMovie = mediaDao.getMedia(movie.id)
        if (savedMovie == null) {
            //The movie has never been saved before
            val savingResult = saveMovie(movie)
            if (!savingResult)
            //Unsuccessful insertion
                return@withContext false
        }

        //Set movie as favorite
        val favDate = Date(System.currentTimeMillis())
        // FIXME: Need to pass the user id as params or saved refs
        val crossRef = UserFavMediaCrossRef(mediaId = movie.id, userId = 0, favDate = favDate)
        val saveAsFavResult = mediaDao.insertUserFavMediaCrossRef(crossRef)
        if (saveAsFavResult <= 0)
        //Unsuccessful insertion
            return@withContext false

        return@withContext true

    }

    suspend fun insertToSeeMovie(movie: Movie): Boolean = withContext(ioDispatcher) {
        // TODO: Come inserire in una lista di default

        return@withContext false
    }

    suspend fun insertSeenMovie(movie: Movie): Boolean = withContext(ioDispatcher) {
        // TODO: Come inserire in una lista di default

        return@withContext false
    }

    suspend fun isFavoriteMovie(movieId: Int): Boolean = withContext(ioDispatcher) {
        // FIXME: How to check 

        return@withContext false
    }

    suspend fun isToSeeMovie(movieId: Int): Boolean = withContext(ioDispatcher) {
        // FIXME: How to check

        return@withContext false
    }

    suspend fun isSeenMovie(movieId: Int): Boolean = withContext(ioDispatcher) {
        // FIXME: How to check

        return@withContext false
    }

    suspend fun getUserFavMedias(): List<Media> = withContext(ioDispatcher) {
        // FIXME: get user id from param or shared prefs
        val result = userDao.getUserFavMedias(userId = 0)

        return@withContext result[0].favMedias.map { it.asDomain() }

    }

    suspend fun getAllToSeeMovie(): List<Media> = withContext(ioDispatcher) {
// FIXME: Come restituire la lista dei film da vedere
        return@withContext emptyList()

    }

    suspend fun getAllSeenMovie(): List<Media> = withContext(ioDispatcher) {
// FIXME: Come restituire la lista dei film visti
        return@withContext emptyList()

    }

    suspend fun removeMovieFromFavorite(movie: Movie): Boolean =
        withContext(ioDispatcher) {
            // FIXME: Pass userId as param or sharedprefs
            val crossRefToDelete = mediaDao.getUserFavMedia(mediaId = movie.id, userId = 0)
                ?: //Fav media to delete does not exists
                return@withContext false

            val deletionResult = mediaDao.deleteUserFavMediaCrossRef(crossRefToDelete)
            return@withContext deletionResult > 0
        }

    suspend fun removeMovieFromSeen(movie: Movie): Boolean =
        withContext(ioDispatcher) {

// FIXME: Come eliminare un film dalla lista dei visti
            return@withContext false
        }

    suspend fun removeMovieFromToSee(movie: Movie): Boolean =
        withContext(ioDispatcher) {
            // FIXME: Come eliminare un film dalla lista da vedere

            return@withContext false
        }

    suspend fun insertMovieInList(listId: Int, movie: Movie, position: Int): Boolean =
        withContext(ioDispatcher) {
            val savedMovie = mediaDao.getMedia(movie.id)
            if (savedMovie == null) {
                //Movie has never been saved
                val savingResult = saveMovie(movie)
                if (!savingResult)
                    return@withContext false
            }
            val insertionDate = Date(System.currentTimeMillis())
            val crossRef = MediaListCrossRef(
                mediaId = movie.id,
                listId = listId,
                insertionDate = insertionDate,
                position = position
            )

            val insertionResult = mediaDao.insertMediaListCrossRef(crossRef)

            return@withContext insertionResult > 0
        }

    suspend fun removeMovieFromList(mediaId:Int, listId:Int):Boolean = withContext(ioDispatcher){
        val crossRefToRemove =
            mediaDao.getMediaInList(listId, mediaId) ?: //Movie never been in list
            return@withContext false

        val deletionResult = mediaDao.deleteMediaFromList(crossRefToRemove)

        return@withContext deletionResult > 0
    }

}