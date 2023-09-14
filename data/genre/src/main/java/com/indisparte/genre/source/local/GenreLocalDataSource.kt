package com.indisparte.genre.source.local

import com.github.ajalt.timberkt.Timber
import com.indisparte.common.Genre
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.model.asDomain
import com.indisparte.database.model.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class GenreLocalDataSource
@Inject
constructor(
    private val dao: GenreDao,
) {
    fun insertGenres(genres: List<Genre>) = flow<Nothing> {
        val mappedEntity = genres.map { it.asEntity() }
        dao.insertGenreList(mappedEntity)
    }.flowOn(Dispatchers.IO)

    fun getAllSavedGenres() = flow {
        val entityData = dao.getAllGenres()
        val mappedData = entityData.map { it.asDomain() }
        emit(mappedData)
    }.flowOn(Dispatchers.IO)

    fun updateGenre(genre: Genre) = flow<Nothing> {
        Timber.tag("GenreLocalDataSource").d("Update $genre")
        val mappedGenre = genre.asEntity()
        dao.updateGenre(mappedGenre)
    }.flowOn(Dispatchers.IO)

    fun getAllGenresById(genresIds: List<Int>): Flow<List<Genre>> = flow{
        val entityGenres = dao.getAllGenresById(genresIds)
        val mappedGenres = entityGenres.map { it.asDomain() }
        emit(mappedGenres)
    }.flowOn(Dispatchers.IO)


}