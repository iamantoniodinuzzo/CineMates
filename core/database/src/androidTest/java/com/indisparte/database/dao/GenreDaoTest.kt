package com.indisparte.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.base.MediaType
import com.indisparte.database.model.GenreEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class GenreDaoTest : BaseDaoTest() {

    private lateinit var dao: GenreDao

    @Before
    override fun setup() {
        super.setup()
        dao = testDatabase.getGenreDao()
    }

    @Test
    fun testInsertAndLoadGenresSuccess() = runBlockingTest {
        //GIVEN
        val genres = listOf(
            GenreEntity(id = 7077, name = "Marietta Leonard", isFavorite = false),
            GenreEntity(id = 5645, name = "Harriett French", isFavorite = false),
            GenreEntity(id = 2524, name = "Ivy Hunter", isFavorite = false)
        )

        //WHEN
        dao.insertGenreList(genres)

        //THEN
        val loadedGenres = dao.getAllGenres()
        assertTrue(loadedGenres.containsAll(genres))

    }

    @Test
    fun testUpdateAndLoadGenresSuccess() = runBlockingTest {
        //GIVEN
        val notMyFavGenre = GenreEntity(id = 5908, name = "Francis Church", isFavorite = false)
        val genres = listOf(
            GenreEntity(id = 7077, name = "Marietta Leonard", isFavorite = false),
            GenreEntity(id = 5645, name = "Harriett French", isFavorite = false),
            GenreEntity(id = 2524, name = "Ivy Hunter", isFavorite = false),
            notMyFavGenre
        )

        //WHEN
        dao.insertGenreList(genres)
        val myFavGenres = notMyFavGenre.copy(isFavorite = true)
        dao.updateGenre(myFavGenres)

        //THEN
        val loadGenres = dao.getAllGenres()
        assertEquals(genres.size, loadGenres.size)
        val genreToCheck = loadGenres.find { it.id == notMyFavGenre.id }
        assertEquals(genreToCheck, myFavGenres)

    }

    @Test
    fun testGetAllGenresByMediaTypeSuccess() = runBlockingTest {
        // GIVEN
        val movieMediaType = MediaType.MOVIE.id
        val MOVIETVMediaType = MediaType.MOVIE_TV.id
        val genres = listOf(
            GenreEntity(
                id = 7077,
                name = "Marietta Leonard",
                isFavorite = false,
                mediaType = movieMediaType
            ),
            GenreEntity(
                id = 5645,
                name = "Harriett French",
                isFavorite = false,
                mediaType = movieMediaType
            ),
            GenreEntity(
                id = 2524,
                name = "Ivy Hunter",
                isFavorite = false,
                mediaType = movieMediaType
            ),
            GenreEntity(
                id = 1234,
                name = "Test Genre",
                isFavorite = false,
                mediaType = MOVIETVMediaType
            )
        )

        // WHEN
        dao.insertGenreList(genres)
        val filteredGenres = dao.getAllGenresByMediaType(movieMediaType)

        // THEN
        assertEquals(genres.size, filteredGenres.size)
    }

    @Test
    fun testGetAllMyFavGenresSuccess() = runBlockingTest {
        // GIVEN
        val genres = listOf(
            GenreEntity(id = 7077, name = "Marietta Leonard", isFavorite = true),
            GenreEntity(id = 5645, name = "Harriett French", isFavorite = false),
            GenreEntity(id = 2524, name = "Ivy Hunter", isFavorite = true),
            GenreEntity(id = 1234, name = "Test Genre", isFavorite = true)
        )

        // WHEN
        dao.insertGenreList(genres)
        val myFavGenres = dao.getAllMyFavGenres()

        // THEN
        assertEquals(3, myFavGenres.size)
    }

    @Test
    fun testGetAllGenresByIdSuccess() = runBlockingTest {
        // GIVEN
        val genreIds = listOf(7077, 2524)
        val genres = listOf(
            GenreEntity(id = 7077, name = "Marietta Leonard", isFavorite = false),
            GenreEntity(id = 5645, name = "Harriett French", isFavorite = false),
            GenreEntity(id = 2524, name = "Ivy Hunter", isFavorite = false)
        )

        // WHEN
        dao.insertGenreList(genres)
        val loadedGenres = dao.getAllGenresById(genreIds)

        // THEN
        assertEquals(2, loadedGenres.size)
    }


}