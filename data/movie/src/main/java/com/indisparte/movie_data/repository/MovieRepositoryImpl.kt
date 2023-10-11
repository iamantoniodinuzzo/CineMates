package com.indisparte.movie_data.repository

import com.indisparte.base.Media
import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.filter.TimeWindow
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDatesByCountry
import com.indisparte.movie_data.source.local.GenreLocalDataSource
import com.indisparte.movie_data.source.local.MovieLocalDataSource
import com.indisparte.movie_data.source.remote.MovieRemoteDataSource
import com.indisparte.movie_data.util.MovieListType
import com.indisparte.network.Result
import com.indisparte.network.whenResources
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class MovieRepositoryImpl
@Inject
constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val genreLocalDataSource: GenreLocalDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {

    override fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>> =
        movieRemoteDataSource.getByListType(movieListType)


    override fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>> =
        movieRemoteDataSource.getTrending(timeWindow)


    override fun getMovieDetailsAndUpdateWithLocalData(movieId: Int): Flow<Result<MovieDetails>> = flow {
        // Get movie details from API and update it with local data
        movieRemoteDataSource.getDetails(movieId).collect { response ->
            response.whenResources(
                onSuccess = { movieDetails ->
                    //get updated genres from local database to check which genre of current movie, is favorite
                    val favoriteLocalGenres =
                        genreLocalDataSource.getAllGenresById(movieDetails.genres.map { it.id })
                            .first()
                    // Update movie details genres with local genre status
                    movieDetails.updateGenres(favoriteLocalGenres)

                    //Check if current movie is a favorite movie
                    val isFavorite = movieLocalDataSource.isFavoriteMedia(movieDetails.id)
                    // Update isFavorite variable
                    movieDetails.isFavorite = isFavorite

                    emit(Result.Success(movieDetails))
                },
                onError = {
                    emit(response)
                },
                onLoading = {
                    emit(response)
                }

            )
        }

    }

    override fun getSimilar(movieId: Int): Flow<Result<List<Movie>>> =
        movieRemoteDataSource.getSimilar(movieId)

    override fun getCast(movieId: Int): Flow<Result<List<Cast>>> =
        movieRemoteDataSource.getCast(movieId)

    override fun getCrew(movieId: Int): Flow<Result<List<Crew>>> =
        movieRemoteDataSource.getCrew(movieId)

    override fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>> = movieRemoteDataSource.getWatchProviders(movieId, country)

    override fun getVideos(movieId: Int): Flow<Result<List<Video>>> =
        movieRemoteDataSource.getVideos(movieId)

    override fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>> =
        movieRemoteDataSource.getReleaseDates(movieId)

    override fun getBackdrop(movieId: Int): Flow<Result<List<Backdrop>>> =
        movieRemoteDataSource.getBackdrop(movieId)

    override fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>> =
        movieRemoteDataSource.getCollectionDetails(collectionId)

    override fun setMovieAsFavorite(movie: Movie): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        val result = movieLocalDataSource.insertFavoriteMovie(movie)
        emit(Result.Success(result))
    }

    override fun isMovieByThisIdFavorite(movieId: Int): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        val result = movieLocalDataSource.isFavoriteMedia(movieId)
        emit(Result.Success(result))

    }

    override fun getAllFavoriteMovies(): Flow<Result<List<Media>>> = flow {
        emit(Result.Loading)
        val result = movieLocalDataSource.getAllFavoriteMedia()
        emit(Result.Success(result))
    }

    override fun removeMovieFromFavorite(movie: Movie) = flow<Nothing> {
        movieLocalDataSource.removeMovieFromFavorite(movie)
    }

}



