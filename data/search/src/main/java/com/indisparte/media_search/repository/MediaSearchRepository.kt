package com.indisparte.media_search.repository

import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import com.indisparte.tv.TvShow
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
interface MediaSearchRepository {

     fun searchMovieByTitle(title:String): Flow<Result<List<Movie>>>
     fun searchTvByTitle(title:String): Flow<Result<List<TvShow>>>
}