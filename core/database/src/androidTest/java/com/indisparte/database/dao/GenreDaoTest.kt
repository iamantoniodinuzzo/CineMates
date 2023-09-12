package com.indisparte.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.database.CineMatesDatabase
import com.indisparte.database.model.GenreEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
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
class GenreDaoTest {

    private lateinit var db: CineMatesDatabase
    private lateinit var dao: GenreDao

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CineMatesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.getGenreDao()
    }

    @Test
    fun testInsertAndLoadGenresSuccess() = runBlocking {
        //GIVEN - tutto quello che ti serve per testare
        val genres = listOf(
            GenreEntity(id = 7077, name = "Marietta Leonard", isFavorite = false),
            GenreEntity(id = 5645, name = "Harriett French", isFavorite = false),
            GenreEntity(id = 2524, name = "Ivy Hunter", isFavorite = false)
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        dao.insertGenreList(genres)

        //THEN - verifica se il test è andato come ti aspettavi
        val loadedGenres = dao.getAllGenres().first()
        assertTrue(loadedGenres.containsAll(genres))

    }

    @Test
    fun testUpdateAndLoadGenresSuccess() = runBlocking {
        //GIVEN - tutto quello che ti serve per testare
        val notMyFavGenre = GenreEntity(id = 5908, name = "Francis Church", isFavorite = false)
        val genres = listOf(
            GenreEntity(id = 7077, name = "Marietta Leonard", isFavorite = false),
            GenreEntity(id = 5645, name = "Harriett French", isFavorite = false),
            GenreEntity(id = 2524, name = "Ivy Hunter", isFavorite = false),
            notMyFavGenre
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        dao.insertGenreList(genres)
        val myFavGenres = notMyFavGenre.copy(isFavorite = true)
        dao.updateGenre(myFavGenres)

        //THEN - verifica se il test è andato come ti aspettavi
        val loadGenres = dao.getAllGenres().first()
        assertEquals(genres.size, loadGenres.size)
        val genreToCheck = loadGenres.find { it.id == notMyFavGenre.id }
        assertEquals(genreToCheck, myFavGenres)

    }

    @After
    fun closeDb() = db.close()

}