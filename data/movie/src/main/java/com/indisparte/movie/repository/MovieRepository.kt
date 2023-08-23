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
     fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>>
     fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>>
     fun getBySearch(query: String): Flow<Result<List<Movie>>>
     fun getDiscoverable(mediaFilter: MediaFilter): Flow<Result<List<Movie>>>
     fun getDetails(movieId: Int): Flow<Result<MovieDetails>>
     fun getSimilar(movieId: Int): Flow<Result<List<Movie>>>
     fun getCast(movieId: Int): Flow<Result<List<Cast>>>
     fun getCrew(movieId: Int): Flow<Result<List<Crew>>>
     fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>>

     fun getVideos(movieId: Int): Flow<Result<List<Video>>>

     fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>>

     fun getBackdrop(movieId: Int):Flow<Result<List<Backdrop>>>
     fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>>

}