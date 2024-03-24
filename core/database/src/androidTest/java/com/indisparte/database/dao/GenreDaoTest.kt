package com.indisparte.database.dao

import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.indisparte.base.MediaType
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.relations.GenreMediaCrossRef
import org.junit.Test
import org.junit.runner.RunWith


/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class GenreDaoTest : BaseDaoTest() {
    private lateinit var genreDao: GenreDao

    override fun setup() {
        super.setup()
        genreDao = testDatabase.genreDao()
    }

    @Test
    fun successfullyGetAllGenres() {
        // Given
        val genre1 = GenreEntity(1, "Action", MediaType.MOVIE.id)
        val genre2 = GenreEntity(2, "Adventure", MediaType.TV.id)
        val genreList = listOf(genre1, genre2)
        genreDao.insertAll(genreList)

        // When
        val retrievedGenres = genreDao.getAllGenres()

        // Then
        assert(retrievedGenres.size == 2)
        assert(retrievedGenres.contains(genre1))
        assert(retrievedGenres.contains(genre2))
    }

    @Test
    fun successfullyGetGenreById() {
        // Given
        val genre = GenreEntity(1, "Action", MediaType.TV.id)
        genreDao.insert(genre)

        // When
        val retrievedGenre = genreDao.getGenreById(1)

        // Then
        assert(retrievedGenre != null)
        assert(retrievedGenre == genre)
    }

    @Test
    fun successfullyGetGGenreByMediaType() {
        // Given
        val genre1 = GenreEntity(1, "Action", MediaType.MOVIE.id)
        val genre2 = GenreEntity(2, "Adventure", MediaType.MOVIE.id)
        val genre3 = GenreEntity(3, "Horror", MediaType.TV.id)
        val genreList = listOf(genre1, genre2, genre3)
        genreDao.insertAll(genreList)

        // When
        val retrievedGenres = genreDao.getAllGenresByMediaType(MediaType.MOVIE.id)

        // Then
        assert(retrievedGenres.size == 2)
        assert(retrievedGenres.contains(genre1))
        assert(retrievedGenres.contains(genre2))
    }

    @Test(expected = UnsupportedOperationException::class)
    fun failedGenreDeletion() {
        // Given
        val genre = GenreEntity(1, "Action", MediaType.TV.id)

        // When
        genreDao.delete(genre)

        // Then
        // Se l'eccezione UnsupportedOperationException non viene sollevata,
        // il test fallirà automaticamente.
    }



}