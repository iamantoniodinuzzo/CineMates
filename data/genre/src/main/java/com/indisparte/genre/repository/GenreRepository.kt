package com.indisparte.genre.repository

import com.indisparte.model.entity.Genre
import com.indisparte.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface GenreRepository {
    suspend fun getMovieGenreList(): Flow<Resource<List<Genre>>>
    suspend fun getTvGenreList(): Flow<Resource<List<Genre>>>
}