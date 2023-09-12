package com.indisparte.genre.source.local

import com.indisparte.common.Genre
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.model.asEntity
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class GenreLocalDataSource
@Inject
constructor(
    private val dao: GenreDao,
) {
    fun insertGenres(genres: List<Genre>) {
        val mappedEntity = genres.map { it.asEntity() }
        dao.insertGenreList(mappedEntity)
    }

    fun getAllSavedGenres() = dao.getAllGenres()

}