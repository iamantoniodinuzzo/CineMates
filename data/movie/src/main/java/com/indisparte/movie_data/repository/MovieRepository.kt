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
import com.indisparte.movie_data.util.MovieListType
import com.indisparte.network.Result
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
interface MovieRepository {
    fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>>
    fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>>
    fun getMovieDetailsAndUpdateWithLocalData(movieId: Int): Flow<Result<MovieDetails>>
    fun getSimilar(movieId: Int): Flow<Result<List<Movie>>>
    fun getCast(movieId: Int): Flow<Result<List<Cast>>>
    fun getCrew(movieId: Int): Flow<Result<List<Crew>>>
    fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>>

    fun getVideos(movieId: Int): Flow<Result<List<Video>>>

    fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>>

    fun getBackdrop(movieId: Int): Flow<Result<List<Backdrop>>>
    fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>>

    fun setMovieAsFavorite(movie: Movie): Flow<Result<Boolean>>

    fun isMovieByThisIdFavorite(movieId: Int): Flow<Result<Boolean>>

    fun getAllFavoriteMovies(): Flow<Result<List<Media>>>

    fun removeMovieFromFavorite(movie: Movie): Flow<Nothing>

}