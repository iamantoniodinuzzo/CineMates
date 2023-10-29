package com.indisparte.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.base.MediaType
import com.indisparte.database.CineMatesDatabase
import com.indisparte.database.model.MediaEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class MediaDaoTest {

    private lateinit var db: CineMatesDatabase
    private lateinit var dao: MediaDao

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CineMatesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.getMediaDao()
    }

    @Test
    fun testInsertAndLoadMediaSuccess() = runBlocking {
        //GIVEN
        val mediaTypeId = MediaType.MOVIE.id
        val media = MediaEntity(
            id = 3651,
            mediaName = "Ben Chase",
            popularity = null,
            posterPath = null,
            voteAverage = 8.9,
            mediaType = mediaTypeId,
        )

        //WHEN
        dao.insertMedia(media)

        //THEN
        val entityList = dao.getAllFavoriteMediaByMediaType(mediaTypeId)
        assertTrue(entityList.contains(media))

    }

    @Test
    fun testUpdateAndLoadMediaSuccess() = runBlocking {
        //GIVEN
        val movieMediaType = MediaType.MOVIE
        val tvMediaType = MediaType.TV
        val notMyFavMedia = MediaEntity(
            id = 3849,
            mediaName = "Edmond Burt",
            popularity = null,
            posterPath = null,
            voteAverage = 10.11,
            mediaType = movieMediaType.id,
        )
        val medias = listOf(
            MediaEntity(
                id = 8385,
                mediaName = "Cleo Blankenship",
                popularity = null,
                posterPath = null,
                voteAverage = 12.13,
                mediaType = tvMediaType.id,
            ),
            MediaEntity(
                id = 9944,
                mediaName = "Alva Robles",
                popularity = null,
                posterPath = null,
                voteAverage = 14.15,
                mediaType = movieMediaType.id

            ),
            notMyFavMedia
        )

        //WHEN
        medias.forEach {
            dao.insertMedia(it)
        }
        dao.insertMedia(notMyFavMedia)

        //THEN
        val loadFavMovies = dao.getAllFavoriteMediaByMediaType(movieMediaType.id)
        assertEquals(2, loadFavMovies.size)
        val mediaToCheck = loadFavMovies.find { it.id == notMyFavMedia.id }
        assertEquals(mediaToCheck, notMyFavMedia)

    }

    @Test
    fun testGetAllFavMediaByMediaTypeSuccess() = runBlocking {
        // GIVEN
        val movieMediaType = MediaType.MOVIE.id
        val tvMediaType = MediaType.TV.id
        val favoriteMovies = listOf(
            MediaEntity(
                id = 3065,
                mediaName = "Latasha Sellers",
                popularity = null,
                posterPath = null,
                voteAverage = 16.17,
                mediaType = movieMediaType,
            ),
            MediaEntity(
                id = 1504,
                mediaName = "Gerard Austin",
                popularity = null,
                posterPath = null,
                voteAverage = 18.19,
                mediaType = movieMediaType,
            )
        )

        val favoriteTvs = listOf(
            MediaEntity(
                id = 5468,
                mediaName = "Jamie Byrd",
                popularity = null,
                posterPath = null,
                voteAverage = 20.21,
                mediaType = tvMediaType,
            ),
            MediaEntity(
                id = 3071,
                mediaName = "Cathy Fitzpatrick",
                popularity = null,
                posterPath = null,
                voteAverage = 22.23,
                mediaType = tvMediaType,
            )
        )
        val notFavMovie = MediaEntity(
            id = 7848,
            mediaName = "Rae Hunt",
            popularity = null,
            posterPath = null,
            voteAverage = 26.27,
            mediaType = movieMediaType,
        )


        val notFavTvShow = MediaEntity(
            id = 6909,
            mediaName = "Wilburn Rodriguez",
            popularity = null,
            posterPath = null,
            voteAverage = 28.29,
            mediaType = tvMediaType,
        )


        // WHEN
        val allMedias = favoriteMovies + favoriteTvs + notFavMovie + notFavTvShow
        allMedias.forEach {
            dao.insertMedia(it)
        }
        val loadedFavoriteMovies = dao.getAllFavoriteMediaByMediaType(movieMediaType)
        val loadedFavoriteTv = dao.getAllFavoriteMediaByMediaType(tvMediaType)

        // THEN
        assertEquals(favoriteMovies.size, loadedFavoriteMovies.size)
        assertEquals(favoriteTvs.size, loadedFavoriteTv.size)
    }


    @Test
    fun testGetMediaByIdSuccess() = runBlocking {
        // GIVEN
        val myId = 7077
        val media = MediaEntity(
            id = myId,
            mediaName = "Lucio Bishop",
            popularity = null,
            posterPath = null,
            voteAverage = 24.25,
            mediaType = MediaType.MOVIE.id,
        )


        // WHEN
        dao.insertMedia(media)
        val loadedMovieById = dao.getMediaById(myId)

        // THEN
        assertEquals(media, loadedMovieById)
    }

    @Test
    fun testGetMediaByIdNull() = runBlocking {
        // GIVEN
        val myId = 7077
        val media = MediaEntity(
            id = 789,
            mediaName = "Lucio Bishop",
            popularity = null,
            posterPath = null,
            voteAverage = 24.25,
            mediaType = MediaType.MOVIE.id,
        )


        // WHEN
        dao.insertMedia(media)
        val loadedMovieById = dao.getMediaById(myId)

        // THEN
        assertNull(loadedMovieById)
    }

    @After
    fun closeDb() = db.close()

}