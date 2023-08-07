package com.indisparte.genre.repository

import com.indisparte.model.entity.Genre
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface GenreRepository {
    suspend fun getMovieGenreList(): Flow<Result<List<Genre>>>
    suspend fun getTvGenreList(): Flow<Result<List<Genre>>>
}