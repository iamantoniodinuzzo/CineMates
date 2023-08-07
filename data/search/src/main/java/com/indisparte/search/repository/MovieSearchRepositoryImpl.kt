package com.indisparte.search.repository

import com.indisparte.model.entity.Movie
import com.indisparte.network.Result
import com.indisparte.network.getListFromResponse
import com.indisparte.search.mapper.toMovie
import com.indisparte.search.source.MovieSearchDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class MovieSearchRepositoryImpl
@Inject
constructor(
    private val movieSearchDataSource: MovieSearchDataSource,
    private val queryMap: Map<String, String>,
) : MovieSearchRepository {

    override suspend fun searchMovieByTitle(title: String): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = { movieSearchDataSource.searchMovieByTitle(title, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )
}