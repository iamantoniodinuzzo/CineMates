package com.indisparte.genre.repository

import com.indisparte.common.Genre
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
interface GenreRepository {
     fun getMovieGenreList(): Flow<Result<List<Genre>>>
     fun getTvGenreList(): Flow<Result<List<Genre>>>
}