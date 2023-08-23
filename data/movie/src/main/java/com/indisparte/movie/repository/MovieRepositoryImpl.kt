package com.indisparte.movie.repository

import com.indisparte.model.MediaFilter
import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.Backdrop
import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.CollectionDetails
import com.indisparte.model.entity.CountryResult
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.MovieDetails
import com.indisparte.model.entity.ReleaseDatesByCountry
import com.indisparte.model.entity.Video
import com.indisparte.movie.mapper.mapToBackdrop
import com.indisparte.movie.mapper.mapToCast
import com.indisparte.movie.mapper.mapToCollectionDetails
import com.indisparte.movie.mapper.mapToCountryResult
import com.indisparte.movie.mapper.mapToCrew
import com.indisparte.movie.mapper.mapToReleaseDatesByCountry
import com.indisparte.movie.mapper.mapToVideo
import com.indisparte.movie.mapper.toMovie
import com.indisparte.movie.mapper.toMovieDetails
import com.indisparte.movie.source.MovieDataSource
import com.indisparte.movie.util.MovieListType
import com.indisparte.network.Result
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

    //todo need to cache this movies
    override  fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getListOfSpecificMovies(movieListType.value, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )


    override  fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getTrending(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )

    override  fun getBySearch(query: String): Flow<Result<List<Movie>>> =
        flow {
            queryMap["query"] = query
            emitAll(getListFromResponse(
                request = { movieService.getBySearch(queryMap) },
                mapper = { response -> response.results.map { it.toMovie() } }
            ))
        }

    override  fun getDiscoverable(mediaFilter: MediaFilter): Flow<Result<List<Movie>>> =
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


    override  fun getDetails(movieId: Int): Flow<Result<MovieDetails>> =
        getSingleFromResponse(
            request = { movieService.getDetails(movieId, queryMap) },
            mapper = { response -> response.toMovieDetails() }
        )


    override  fun getSimilar(movieId: Int): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getSimilar(movieId, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )

    override  fun getCast(movieId: Int): Flow<Result<List<Cast>>> =
        getListFromResponse(
            request = { movieService.getCredits(movieId, queryMap) },
            mapper = { response -> response.cast.map { it.mapToCast() } }
        )

    override  fun getCrew(movieId: Int): Flow<Result<List<Crew>>> =
        getListFromResponse(
            request = { movieService.getCredits(movieId, queryMap) },
            mapper = { response -> response.crew.map { it.mapToCrew() } }
        )

    override  fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>> = getSingleFromResponse(
        request = { movieService.getWatchProviders(movieId, queryMap) },
        mapper = { response ->
            response.getCountryResultByCountry(country)?.mapToCountryResult()
        }
    )

    override  fun getVideos(movieId: Int): Flow<Result<List<Video>>> =
        getListFromResponse(
            request = { movieService.getVideos(movieId, queryMap) },
            mapper = { response -> response.results.map { it.mapToVideo() } }
        )

    override  fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>> =
        getListFromResponse(
            request = { movieService.getReleaseDates(movieId, queryMap) },
            mapper = { response ->
                response.results.map { it.mapToReleaseDatesByCountry() }
            }
        )

    override  fun getBackdrop(movieId: Int): Flow<Result<List<Backdrop>>> =
        getListFromResponse(
            request = { movieService.getImages(movieId, queryMap) },
            mapper = { response ->
                response.backdrops.map { it.mapToBackdrop() }
            }
        )

    override  fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>> =
        getSingleFromResponse(
            request = { movieService.getCollectionDetails(collectionId, queryMap) },
            mapper = { response ->
                response.mapToCollectionDetails()
            }
        )

}



