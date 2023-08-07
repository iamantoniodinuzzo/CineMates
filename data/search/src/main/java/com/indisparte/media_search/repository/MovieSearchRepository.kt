package com.indisparte.media_search.repository

import com.indisparte.model.entity.Movie
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
interface MovieSearchRepository {

    suspend fun searchMovieByTitle(title:String): Flow<Result<List<Movie>>>
}