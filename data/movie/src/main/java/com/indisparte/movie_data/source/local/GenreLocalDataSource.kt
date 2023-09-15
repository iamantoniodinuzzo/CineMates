package com.indisparte.movie_data.source.local

import com.indisparte.common.Genre
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.model.asDomain
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
    fun getAllGenresById(genresIds: List<Int>): Flow<List<Genre>> = flow{
        val entityGenres = dao.getAllGenresById(genresIds)
        val mappedGenres = entityGenres.map { it.asDomain() }
        emit(mappedGenres)
    }.flowOn(Dispatchers.IO)

}