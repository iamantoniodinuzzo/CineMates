package com.indisparte.media_search.repository

import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import com.indisparte.network.getListFromResponse
import com.indisparte.media_search.mapper.toMovie
import com.indisparte.media_search.source.MediaSearchDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class MediaSearchRepositoryImpl
@Inject
constructor(
    private val mediaSearchDataSource: MediaSearchDataSource,
    private val queryMap: MutableMap<String, String>,
) : MediaSearchRepository {

    override  fun searchMovieByTitle(title: String): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = { mediaSearchDataSource.searchMovieByTitle(title, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )
}