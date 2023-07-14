package com.indisparte.movie.repository

import com.indisparte.model.MediaFilter
import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.MovieDetails
import com.indisparte.movie.mapper.mapToCast
import com.indisparte.movie.mapper.mapToCrew
import com.indisparte.movie.mapper.toMovie
import com.indisparte.movie.mapper.toMovieDetails
import com.indisparte.movie.source.MovieDataSource
import com.indisparte.movie.util.MovieListType
import com.indisparte.network.Resource
import com.indisparte.network.getListFromResponse
import com.indisparte.network.getSingleFromResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieRepositoryImpl
@Inject
constructor(
    private val movieService: MovieDataSource,
    private val queryMap: MutableMap<String, String>,
) : MovieRepository {

    override suspend fun getByListType(movieListType: MovieListType): Flow<Resource<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getListOfSpecificMovies(movieListType.value, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )


    override suspend fun getTrending(timeWindow: TimeWindow): Flow<Resource<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getTrending(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )

    override suspend fun getBySearch(query: String): Flow<Resource<List<Movie>>> =
        flow {
            queryMap["query"] = query
            emitAll(getListFromResponse(
                request = { movieService.getBySearch(queryMap) },
                mapper = { response -> response.results.map { it.toMovie() } }
            ))
        }

    override suspend fun getDiscoverable(mediaFilter: MediaFilter): Flow<Resource<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getByDiscover(createQueryParams(mediaFilter)) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )

    private fun createQueryParams(mediaFilter: MediaFilter): Map<String, String> {
        val queryParams = mutableMapOf<String, String>()

        mediaFilter.sortBy.let {
            queryParams["sort_by"] = it.toString()
        }

        mediaFilter.genresIdAsString?.let {
            queryParams["with_genres"] = it
        }

        mediaFilter.year?.let {
            queryParams["year"] = it.toString()
        }

        return queryParams
    }


    override suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>> =
        getSingleFromResponse(
            request = { movieService.getDetails(movieId, queryMap) },
            mapper = { response -> response.toMovieDetails() }
        )


    override suspend fun getSimilar(movieId: Int): Flow<Resource<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getSimilar(movieId, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )

    override suspend fun getCast(movieId: Int): Flow<Resource<List<Cast>>> =
        getListFromResponse(
            request = { movieService.getCredits(movieId, queryMap) },
            mapper = { response -> response.cast.map { it.mapToCast() } }
        )

    override suspend fun getCrew(movieId: Int): Flow<Resource<List<Crew>>> =
        getListFromResponse(
            request = { movieService.getCredits(movieId, queryMap) },
            mapper = { response -> response.crew.map { it.mapToCrew() } }
        )


}
