package com.indisparte.movie_data.source.remote

import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDatesByCountry
import com.indisparte.movie_data.mapper.mapToBackdrop
import com.indisparte.movie_data.mapper.mapToCast
import com.indisparte.movie_data.mapper.mapToCollectionDetails
import com.indisparte.movie_data.mapper.mapToCountryResult
import com.indisparte.movie_data.mapper.mapToCrew
import com.indisparte.movie_data.mapper.mapToReleaseDatesByCountry
import com.indisparte.movie_data.mapper.mapToVideo
import com.indisparte.movie_data.mapper.toMovie
import com.indisparte.movie_data.mapper.toMovieDetails
import com.indisparte.movie_data.util.MovieListType
import com.indisparte.network.util.Result
import com.indisparte.network.response.getListFromResponse
import com.indisparte.network.response.getSingleFromResponse
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class MovieRemoteDataSource
@Inject
constructor(
    private val movieService: MovieDataSource,
    private val queryMap: MutableMap<String, String>,
) {

     fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getListOfSpecificMovies(movieListType.value, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )


     fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getTrending(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )


     fun getDetails(movieId: Int): Flow<Result<MovieDetails>> =
        getSingleFromResponse(
            request = { movieService.getDetails(movieId, queryMap) },
            mapper = { response -> response.toMovieDetails() }
        )


     fun getSimilar(movieId: Int): Flow<Result<List<Movie>>> =
        getListFromResponse(
            request = { movieService.getSimilar(movieId, queryMap) },
            mapper = { response -> response.results.map { it.toMovie() } }
        )

     fun getCast(movieId: Int): Flow<Result<List<Cast>>> =
        getListFromResponse(
            request = { movieService.getCredits(movieId, queryMap) },
            mapper = { response -> response.cast.map { it.mapToCast() } }
        )

     fun getCrew(movieId: Int): Flow<Result<List<Crew>>> =
        getListFromResponse(
            request = { movieService.getCredits(movieId, queryMap) },
            mapper = { response -> response.crew.map { it.mapToCrew() } }
        )

     fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>> = getSingleFromResponse(
        request = { movieService.getWatchProviders(movieId, queryMap) },
        mapper = { response ->
            response.getCountryResultByCountry(country)?.mapToCountryResult()
        }
    )

     fun getVideos(movieId: Int): Flow<Result<List<Video>>> =
        getListFromResponse(
            request = { movieService.getVideos(movieId, queryMap) },
            mapper = { response -> response.results.map { it.mapToVideo() } }
        )

     fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>> =
        getListFromResponse(
            request = { movieService.getReleaseDates(movieId, queryMap) },
            mapper = { response ->
                response.results.map { it.mapToReleaseDatesByCountry() }
            }
        )

     fun getBackdrop(movieId: Int): Flow<Result<List<Backdrop>>> =
        getListFromResponse(
            request = { movieService.getImages(movieId, queryMap) },
            mapper = { response ->
                response.backdrops.map { it.mapToBackdrop() }
            }
        )

     fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>> =
        getSingleFromResponse(
            request = { movieService.getCollectionDetails(collectionId, queryMap) },
            mapper = { response ->
                response.mapToCollectionDetails()
            }
        )
}