package com.indisparte.discover.repository

import com.indisparte.discover.mapper.toMovie
import com.indisparte.discover.source.DiscoverMovieDataSource
import com.indisparte.discover.util.MediaDiscoverFilter
import com.indisparte.model.entity.Movie
import com.indisparte.network.Result
import com.indisparte.network.getListFromResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class MovieDiscoverRepositoryImpl
@Inject
constructor(
    private val movieDataSource: DiscoverMovieDataSource,
    private val queryMap: MutableMap<String, String>,
) : MovieDiscoverRepository {

    override suspend fun discoverMoviesByFilter(movieFilter: MediaDiscoverFilter): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = {
                movieDataSource.discoverMovies(
                    sortBy = movieFilter.sortBy?.sortType,
                    voteAverageGTE = movieFilter.voteAverageGTE,
                    castIds = movieFilter.castIdsCommaSeparated,
                    genreIds = movieFilter.genreIdsCommaSeparated,
                    runtimeLTe = movieFilter.withRuntimeLTe,
                    map = queryMap
                )
            },
            mapper = { response ->
                response.results.map { it.toMovie() }
            }
        )

}