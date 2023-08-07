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
import com.indisparte.movie.util.MovieListType
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface MovieRepository {
    suspend fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>>
    suspend fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>>
    suspend fun getBySearch(query: String): Flow<Result<List<Movie>>>
    suspend fun getDiscoverable(mediaFilter: MediaFilter): Flow<Result<List<Movie>>>
    suspend fun getDetails(movieId: Int): Flow<Result<MovieDetails>>
    suspend fun getSimilar(movieId: Int): Flow<Result<List<Movie>>>
    suspend fun getCast(movieId: Int): Flow<Result<List<Cast>>>
    suspend fun getCrew(movieId: Int): Flow<Result<List<Crew>>>
    suspend fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>>

    suspend fun getVideos(movieId: Int): Flow<Result<List<Video>>>

    suspend fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>>

    suspend fun getBackdrop(movieId: Int):Flow<Result<List<Backdrop>>>
    suspend fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>>

}