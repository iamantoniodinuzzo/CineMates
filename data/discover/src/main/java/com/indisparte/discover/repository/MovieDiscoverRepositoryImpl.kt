package com.indisparte.discover.repository

import com.indisparte.discover.mapper.toMovie
import com.indisparte.discover.source.DiscoverMovieDataSource
import com.indisparte.filter.MediaDiscoverFilter
import com.indisparte.movie_data.Movie
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

    override fun discoverMoviesByFilter(movieFilter: MediaDiscoverFilter): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = {
                movieDataSource.discoverMovies(
                    sortBy = movieFilter.sortBy?.order,
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