package com.indisparte.movie_data.repository

import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDatesByCountry
import com.indisparte.movie_data.util.MovieListType
import com.indisparte.network.Result
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeMovieRepository : MovieRepository {
    private val movies = mutableListOf<Movie>()
    private val movieDetailsMap = mutableMapOf<Int, MovieDetails>()
    private val similarMoviesMap = mutableMapOf<Int, List<Movie>>()
    private val castMap = mutableMapOf<Int, List<Cast>>()
    private val crewMap = mutableMapOf<Int, List<Crew>>()
    private val watchProvidersMap = mutableMapOf<Int, CountryResult?>()
    private val videosMap = mutableMapOf<Int, List<Video>>()
    private val releaseDatesMap = mutableMapOf<Int, List<ReleaseDatesByCountry>>()
    private val backdropsMap = mutableMapOf<Int, List<Backdrop>>()
    private val collectionDetailsMap = mutableMapOf<Int, CollectionDetails>()

    // Helper methods to set fake data in the fake repository
    fun addMovie(movie: Movie) {
        movies.add(movie)
    }

    fun addWatchProviders(watchProviderId: Int, countryResult: CountryResult?) {
        watchProvidersMap[watchProviderId] = countryResult
    }

    fun addVideos(movieId: Int, videos: List<Video>) {
        videosMap[movieId] = videos
    }

    fun addReleaseDates(movieId: Int, releaseDates: List<ReleaseDatesByCountry>) {
        releaseDatesMap[movieId] = releaseDates
    }

    fun addBackdrops(movieId: Int, backdrops: List<Backdrop>) {
        backdropsMap[movieId] = backdrops
    }

    fun addCollectionDetails(movieId: Int, collectionDetails: CollectionDetails) {
        collectionDetailsMap[movieId] = collectionDetails
    }

    fun addMovieDetails(movieId: Int, details: MovieDetails) {
        movieDetailsMap[movieId] = details
    }

    fun addSimilarMovies(movieId: Int, similarMovies: List<Movie>) {
        similarMoviesMap[movieId] = similarMovies
    }

    fun addCast(castId: Int, cast: List<Cast>) {
        castMap[castId] = cast
    }

    fun addCrew(crewId: Int, crew: List<Crew>) {
        crewMap[crewId] = crew
    }


    // Helper method to clear all fake data
    fun clearData() {
        movies.clear()
        collectionDetailsMap.clear()
        backdropsMap.clear()
        movieDetailsMap.clear()
        watchProvidersMap.clear()
        similarMoviesMap.clear()
        crewMap.clear()
        similarMoviesMap.clear()
        videosMap.clear()
        castMap.clear()
        releaseDatesMap.clear()
        // Clear data for other functions as well
    }

    override fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>> {
        // Implement the behavior to return fake data for getByListType
        return flow { emit(Result.Success(movies)) }
    }

    override fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>> {
        // Implement the behavior to return fake data for getTrending
        return flow { emit(Result.Success(movies)) }
    }

    override fun getDetails(movieId: Int): Flow<Result<MovieDetails>> {
        // Implement the behavior to return fake data for getDetails
        val fakeData = movieDetailsMap[movieId]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getSimilar(movieId: Int): Flow<Result<List<Movie>>> {
        // Implement the behavior to return fake data for getSimilar
        val fakeData = similarMoviesMap[movieId]
        return flow { emit(Result.Success(fakeData ?: emptyList())) }
    }

    override fun getCast(movieId: Int): Flow<Result<List<Cast>>> {
        val fakeData = castMap[movieId]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getCrew(movieId: Int): Flow<Result<List<Crew>>> {
        val fakeData = crewMap[movieId]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>> {
        val fakeData = watchProvidersMap[movieId]
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getVideos(movieId: Int): Flow<Result<List<Video>>> {
        val fakeData = videosMap[movieId]
        return flow { emit(Result.Success(fakeData ?: emptyList())) }
    }

    override fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>> {
        val fakeData = releaseDatesMap[movieId]
        return flow { emit(Result.Success(fakeData ?: emptyList())) }
    }

    override fun getBackdrop(movieId: Int): Flow<Result<List<Backdrop>>> {
        val fakeData = backdropsMap[movieId]
        return flow { emit(Result.Success(fakeData ?: emptyList())) }
    }

    override fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>> {
        val fakeData = collectionDetailsMap[collectionId]!!
        return flow { emit(Result.Success(fakeData)) }
    }


}
