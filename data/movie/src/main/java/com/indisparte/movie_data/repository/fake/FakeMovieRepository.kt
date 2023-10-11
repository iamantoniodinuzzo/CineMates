package com.indisparte.movie_data.repository.fake

import com.indisparte.base.Media
import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDate
import com.indisparte.movie_data.ReleaseDatesByCountry
import com.indisparte.movie_data.repository.MovieRepository
import com.indisparte.movie_data.util.MovieListType
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
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
    private val releaseDatesByCountryMap = mutableMapOf<Int, List<ReleaseDatesByCountry>>()
    private val releaseDatesMap = mutableMapOf<Int, List<ReleaseDate>>()
    private val backdropsMap = mutableMapOf<Int, List<Backdrop>>()
    private val collectionDetailsMap = mutableMapOf<Int, CollectionDetails?>()
    private var cineMatesExceptions: CineMatesExceptions? = null
    private var shouldEmitException: Boolean = false

    // Helper methods to set fake data in the fake repository
    fun addMovie(movie: Movie) {
        movies.add(movie)
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
    }

    fun setShouldEmitException(emit: Boolean) {
        shouldEmitException = emit
    }

    fun setExceptionToEmit(cineMatesExceptions: CineMatesExceptions) {
        this.cineMatesExceptions = cineMatesExceptions
    }

    fun addWatchProviders(watchProviderId: Int, countryResult: CountryResult?) {
        watchProvidersMap[watchProviderId] = countryResult
    }

    fun addVideos(movieId: Int, videos: List<Video>) {
        videosMap[movieId] = videos
    }

    fun addReleaseDatesByCountry(movieId: Int, releaseDatesByCountries: List<ReleaseDatesByCountry>) {
        releaseDatesByCountryMap[movieId] = releaseDatesByCountries
    }

    fun addReleaseDates(movieId: Int, releaseDates: List<ReleaseDate>){
        releaseDatesMap[movieId] = releaseDates
    }


    fun addBackdrops(movieId: Int, backdrops: List<Backdrop>) {
        backdropsMap[movieId] = backdrops
    }

    fun addCollectionDetails(collectionId: Int, collectionDetails: CollectionDetails) {
        collectionDetailsMap[collectionId] = collectionDetails
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
        releaseDatesByCountryMap.clear()
        // Clear data for other functions as well
    }

    private fun <T> emitResult(data: T?): Flow<Result<T>> {
        return if (shouldEmitException || data == null) {
            flow {
                emit(Result.Error(cineMatesExceptions ?: CineMatesExceptions.GenericException))
            }
        } else {
            flow {
                emit(Result.Success(data))
            }
        }
    }

    override fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>> {
        return emitResult(movies)
    }

    override fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>> {
        return emitResult(movies)
    }

    override fun getMovieDetailsAndUpdateWithLocalData(movieId: Int): Flow<Result<MovieDetails>> {
        val fakeData = movieDetailsMap[movieId]
        return emitResult(fakeData)
    }

    override fun getSimilar(movieId: Int): Flow<Result<List<Movie>>> {
        val fakeData = similarMoviesMap[movieId]
        return emitResult(fakeData)
    }

    override fun getCast(movieId: Int): Flow<Result<List<Cast>>> {
        val fakeData = castMap[movieId]
        return emitResult(fakeData)
    }

    override fun getCrew(movieId: Int): Flow<Result<List<Crew>>> {
        val fakeData = crewMap[movieId]
        return emitResult(fakeData)
    }

    override fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>> {
        val fakeData = watchProvidersMap[movieId]
        return emitResult(fakeData)
    }

    override fun getVideos(movieId: Int): Flow<Result<List<Video>>> {
        val fakeData = videosMap[movieId]
        return emitResult(fakeData)
    }

    override fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>> {
        val fakeData = releaseDatesByCountryMap[movieId]
        return emitResult(fakeData)
    }

    override fun getBackdrop(movieId: Int): Flow<Result<List<Backdrop>>> {
        val fakeData = backdropsMap[movieId]
        return emitResult(fakeData)
    }

    override fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>> {
        val fakeData = collectionDetailsMap[collectionId]
        return emitResult(fakeData)
    }

    override fun setMovieAsFavorite(movie: Movie): Flow<Nothing> {
        TODO("Not yet implemented")
    }

    override fun isMovieByThisIdFavorite(movieId: Int): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun getAllFavoriteMovies(): Flow<Result<List<Media>>> {
        TODO("Not yet implemented")
    }

    override fun removeMovieFromFavorite(movie: Movie): Flow<Nothing> {
        TODO("Not yet implemented")
    }


}
